package com.vendedor.app.feature.listing.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Publish
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.vendedor.app.R
import com.vendedor.app.feature.listing.viewmodel.ListingViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingDetailScreen(
    onBack: () -> Unit,
    onEdit: (Long) -> Unit,
    onShipping: (Long) -> Unit,
    onPublish: (Long) -> Unit,
    viewModel: ListingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(stringResource(R.string.delete)) },
            text = { Text("¿Eliminar este artículo?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteItem(onBack)
                    showDeleteDialog = false
                }) {
                    Text(stringResource(R.string.delete), color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.title.ifBlank { stringResource(R.string.status_draft) }) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.cancel))
                    }
                },
                actions = {
                    IconButton(onClick = { showDeleteDialog = true }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete),
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Photos
            if (uiState.photos.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(vertical = 4.dp)
                ) {
                    items(uiState.photos) { photo ->
                        AsyncImage(
                            model = File(photo.filePath),
                            contentDescription = null,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
            }

            // Status badge
            StatusBadge(status = uiState.status)
            Spacer(Modifier.height(12.dp))

            // Title
            Text(uiState.title.ifBlank { "—" }, style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))

            // Price
            if (uiState.askingPrice.isNotBlank()) {
                Text(
                    "\$${uiState.askingPrice}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(12.dp))
            }

            // Description
            if (uiState.description.isNotBlank()) {
                Text(uiState.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(12.dp))
            }

            // Details card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (uiState.category.isNotBlank()) {
                        DetailRow(stringResource(R.string.category), uiState.category)
                    }
                    if (uiState.brand.isNotBlank()) {
                        DetailRow(stringResource(R.string.brand), uiState.brand)
                    }
                    if (uiState.condition.isNotBlank()) {
                        DetailRow(stringResource(R.string.condition), uiState.condition)
                    }
                    if (uiState.lengthInches.isNotBlank()) {
                        DetailRow(
                            stringResource(R.string.dimensions),
                            "${uiState.lengthInches} x ${uiState.widthInches} x ${uiState.heightInches} in"
                        )
                    }
                    if (uiState.weightOz.isNotBlank()) {
                        DetailRow(stringResource(R.string.weight), "${uiState.weightOz} oz")
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // Action buttons
            Button(
                onClick = { onEdit(uiState.itemId) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.edit))
            }

            Spacer(Modifier.height(8.dp))

            OutlinedButton(
                onClick = { onShipping(uiState.itemId) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.LocalShipping, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.shipping_info))
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { onPublish(uiState.itemId) },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Icon(Icons.Default.Publish, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.publish))
            }
        }
    }
}

@Composable
private fun StatusBadge(status: String) {
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

@Composable
private fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.bodyMedium)
    }
}
