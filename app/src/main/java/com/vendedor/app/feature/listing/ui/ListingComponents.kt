package com.vendedor.app.feature.listing.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vendedor.app.R

@Composable
fun StatusBadge(status: String) {
    val (text, color) = when (status) {
        "draft" -> stringResource(R.string.status_draft) to MaterialTheme.colorScheme.outline
        "listed" -> stringResource(R.string.status_listed) to MaterialTheme.colorScheme.primary
        "sold" -> stringResource(R.string.status_sold) to MaterialTheme.colorScheme.tertiary
        "archived" -> stringResource(R.string.status_archived) to MaterialTheme.colorScheme.onSurfaceVariant
        else -> status to MaterialTheme.colorScheme.outline
    }
    Card(
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.15f))
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium,
            color = color
        )
    }
}
