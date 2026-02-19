# Vendedor Local Vision (No per-image API fees)

This gives you a local drop-in classifier for item photos using Ollama vision models.

## 1) Install + start Ollama

```bash
brew install ollama
ollama serve
```

In another terminal, pull a vision model:

```bash
ollama pull llava:13b
```

> If you want faster/lighter inference on Apple Silicon, you can try `llava:7b`.

## 2) Run the classifier

From the repo root:

```bash
python3 local_vision/classify.py /path/to/photo.jpg --pretty
```

Output is JSON matching your existing app schema:

- `title`
- `category`
- `subcategory`
- `brand`
- `condition`
- `description`
- `estimatedLengthInches`
- `estimatedWidthInches`
- `estimatedHeightInches`
- `estimatedWeightOz`
- `estimatedPriceLow`
- `estimatedPriceHigh`
- `suggestedSearchTerms`

## Notes

- Fully local once model is pulled (no per-call API charges).
- Accuracy on pricing is heuristic; for best pricing you should still compare against sold listings.
- If Ollama is not reachable, start it with:

```bash
ollama serve
```
