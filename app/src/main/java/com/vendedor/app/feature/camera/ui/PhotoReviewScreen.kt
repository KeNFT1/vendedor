package com.vendedor.app.feature.camera.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vendedor.app.R
import com.vendedor.app.feature.camera.viewmodel.CameraViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoReviewScreen(
    onBack: () -> Unit,
    onContinue: (Long) -> Unit,
    viewModel: CameraViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    var selectedIndex by remember { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.select_main_photo),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cancel)
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
                .padding(16.dp)
        ) {
            // Main photo display
            if (uiState.capturedPhotos.isNotEmpty()) {
                val currentPhoto = uiState.capturedPhotos[selectedIndex]

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Box {
                        AsyncImage(
                            model = File(currentPhoto.filePath),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        if (currentPhoto.isPrimary) {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .padding(8.dp)
                                    .background(
                                        MaterialTheme.colorScheme.primary,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Star,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    stringResource(R.string.main_photo),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            }
                        }

                        // Delete button
                        IconButton(
                            onClick = {
                                viewModel.removePhoto(selectedIndex)
                                if (selectedIndex > 0) selectedIndex--
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .background(
                                    MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
                                    RoundedCornerShape(8.dp)
                                )
                        ) {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = stringResource(R.string.delete),
                                tint = MaterialTheme.colorScheme.onError
                            )
                        }
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Photo thumbnail strip
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    itemsIndexed(uiState.capturedPhotos) { index, photo ->
                        PhotoThumbnail(
                            filePath = photo.filePath,
                            isSelected = index == selectedIndex,
                            isPrimary = photo.isPrimary,
                            onClick = { selectedIndex = index },
                            onSetPrimary = { viewModel.setPrimaryPhoto(index) }
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = stringResource(R.string.select_main_photo),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(Modifier.weight(1f))

            // Continue button
            Button(
                onClick = { viewModel.saveItemWithPhotos() },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.capturedPhotos.isNotEmpty() && !uiState.isSaving
            ) {
                Text(stringResource(R.string.identify_item))
                Spacer(Modifier.width(8.dp))
                Icon(
                    Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun PhotoThumbnail(
    filePath: String,
    isSelected: Boolean,
    isPrimary: Boolean,
    onClick: () -> Unit,
    onSetPrimary: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(72.dp)
            .clip(RoundedCornerShape(8.dp))
            .then(
                if (isSelected) {
                    Modifier.border(
                        BorderStroke(3.dp, MaterialTheme.colorScheme.primary),
                        RoundedCornerShape(8.dp)
                    )
                } else Modifier
            )
            .clickable(onClick = onClick)
    ) {
        AsyncImage(
            model = File(filePath),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Star icon to set as primary
        IconButton(
            onClick = onSetPrimary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(24.dp)
        ) {
            Icon(
                imageVector = if (isPrimary) Icons.Default.Star else Icons.Default.StarBorder,
                contentDescription = stringResource(R.string.main_photo),
                tint = if (isPrimary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
