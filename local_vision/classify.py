#!/usr/bin/env python3
"""
Local item identification for Vendedor using Ollama vision models.

Usage:
  python3 local_vision/classify.py /path/to/image.jpg
  python3 local_vision/classify.py /path/to/image.jpg --model llava:13b --pretty
"""

from __future__ import annotations

import argparse
import base64
import json
import mimetypes
import pathlib
import sys
from typing import Any, Dict
from urllib import request, error

OLLAMA_URL = "http://127.0.0.1:11434/api/chat"
DEFAULT_MODEL = "llava:13b"

SCHEMA_TEMPLATE = {
    "title": "",
    "category": "",
    "subcategory": "",
    "brand": None,
    "condition": "good",
    "description": "",
    "estimatedLengthInches": None,
    "estimatedWidthInches": None,
    "estimatedHeightInches": None,
    "estimatedWeightOz": None,
    "estimatedPriceLow": 0,
    "estimatedPriceHigh": 0,
    "suggestedSearchTerms": ["", "", ""],
}

PROMPT = """You are a product identification assistant for a resale marketplace app.
Analyze the provided photo and return ONLY a valid JSON object with this exact shape:

{schema}

Rules:
- title: concise listing title (<=80 chars)
- category/subcategory: marketplace friendly
- brand: null if unknown
- condition: one of new, like_new, good, fair, poor
- description: 2-3 listing-style sentences
- dimensions/weight: numeric estimate or null
- price low/high: realistic used resale USD numbers
- suggestedSearchTerms: 3-6 high-value search phrases
- NEVER wrap in markdown
""".strip()


def encode_image(path: pathlib.Path) -> str:
    return base64.b64encode(path.read_bytes()).decode("utf-8")


def detect_mime(path: pathlib.Path) -> str:
    mime, _ = mimetypes.guess_type(str(path))
    return mime or "image/jpeg"


def coerce_number(v: Any) -> float | None:
    if v is None or v == "":
        return None
    try:
        return float(v)
    except (TypeError, ValueError):
        return None


def normalize_payload(payload: Dict[str, Any]) -> Dict[str, Any]:
    out: Dict[str, Any] = dict(payload)

    # Ensure required keys exist.
    for k, default in SCHEMA_TEMPLATE.items():
        out.setdefault(k, default)

    # Normalize known numeric fields.
    for key in [
        "estimatedLengthInches",
        "estimatedWidthInches",
        "estimatedHeightInches",
        "estimatedWeightOz",
        "estimatedPriceLow",
        "estimatedPriceHigh",
    ]:
        out[key] = coerce_number(out.get(key))

    # Cleanup text fields.
    out["title"] = str(out.get("title") or "").strip()
    out["description"] = str(out.get("description") or "").strip()
    out["category"] = str(out.get("category") or "").strip()
    out["subcategory"] = str(out.get("subcategory") or "").strip()

    brand = out.get("brand")
    out["brand"] = None if brand in (None, "", "null", "unknown") else str(brand).strip()

    condition = str(out.get("condition") or "good").strip().lower()
    if condition not in {"new", "like_new", "good", "fair", "poor"}:
        condition = "good"
    out["condition"] = condition

    terms = out.get("suggestedSearchTerms")
    if not isinstance(terms, list):
        terms = []
    out["suggestedSearchTerms"] = [str(t).strip() for t in terms if str(t).strip()][:6]

    # Guard price ordering.
    low = out.get("estimatedPriceLow")
    high = out.get("estimatedPriceHigh")
    if low is not None and high is not None and low > high:
        out["estimatedPriceLow"], out["estimatedPriceHigh"] = high, low

    return out


def run_ollama(image_path: pathlib.Path, model: str, timeout: int) -> Dict[str, Any]:
    image_b64 = encode_image(image_path)

    body = {
        "model": model,
        "format": "json",
        "stream": False,
        "messages": [
            {
                "role": "user",
                "content": PROMPT.format(schema=json.dumps(SCHEMA_TEMPLATE, indent=2)),
                # Ollama expects raw base64 image bytes (not data: URI).
                "images": [image_b64],
            }
        ],
    }

    req = request.Request(
        OLLAMA_URL,
        data=json.dumps(body).encode("utf-8"),
        headers={"Content-Type": "application/json"},
        method="POST",
    )

    try:
        with request.urlopen(req, timeout=timeout) as resp:
            raw = resp.read().decode("utf-8")
    except error.HTTPError as e:
        details = e.read().decode("utf-8", errors="ignore") if hasattr(e, "read") else ""
        raise RuntimeError(f"Ollama API error ({e.code}): {details[:500]}") from e
    except error.URLError as e:
        raise RuntimeError(
            "Cannot reach Ollama at http://127.0.0.1:11434. Start it with: ollama serve"
        ) from e

    envelope = json.loads(raw)
    message = envelope.get("message", {})
    content = (message.get("content") or "").strip()

    try:
        parsed = json.loads(content)
    except json.JSONDecodeError as e:
        raise RuntimeError(f"Model returned non-JSON content: {content[:500]}") from e

    if not isinstance(parsed, dict):
        raise RuntimeError("Model JSON output was not an object")

    return normalize_payload(parsed)


def main() -> int:
    parser = argparse.ArgumentParser(description="Local image classifier for Vendedor")
    parser.add_argument("image", help="Path to image file")
    parser.add_argument("--model", default=DEFAULT_MODEL, help=f"Ollama model (default: {DEFAULT_MODEL})")
    parser.add_argument("--timeout", type=int, default=120, help="HTTP timeout in seconds")
    parser.add_argument("--pretty", action="store_true", help="Pretty-print JSON")
    args = parser.parse_args()

    image_path = pathlib.Path(args.image).expanduser().resolve()
    if not image_path.exists():
        print(f"Image not found: {image_path}", file=sys.stderr)
        return 2

    try:
        result = run_ollama(image_path, args.model, args.timeout)
    except Exception as e:
        print(f"Error: {e}", file=sys.stderr)
        return 1

    if args.pretty:
        print(json.dumps(result, indent=2))
    else:
        print(json.dumps(result, separators=(",", ":")))

    return 0


if __name__ == "__main__":
    raise SystemExit(main())
