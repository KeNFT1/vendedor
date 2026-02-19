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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.vendedor.app.R
import com.vendedor.app.feature.listing.viewmodel.ListingViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListingFormScreen(
    onBack: () -> Unit,
    onSaved: () -> Unit,
    viewModel: ListingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.create_listing)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.cancel))
                    }
                }
            )
        }
    ) { paddingValues ->
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(32.dp)
            )
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Photos strip
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
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
            }

            // Title
            OutlinedTextField(
                value = uiState.title,
                onValueChange = { viewModel.updateTitle(it) },
                label = { Text(stringResource(R.string.title)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            // Description
            OutlinedTextField(
                value = uiState.description,
                onValueChange = { viewModel.updateDescription(it) },
                label = { Text(stringResource(R.string.description)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 6
            )

            Spacer(Modifier.height(12.dp))

            // Category & Brand
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = uiState.category,
                    onValueChange = { viewModel.updateCategory(it) },
                    label = { Text(stringResource(R.string.category)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = uiState.brand,
                    onValueChange = { viewModel.updateBrand(it) },
                    label = { Text(stringResource(R.string.brand)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            Spacer(Modifier.height(12.dp))

            // Condition
            ConditionDropdown(
                selectedCondition = uiState.condition,
                onConditionSelected = { viewModel.updateCondition(it) }
            )

            Spacer(Modifier.height(12.dp))

            // Dimensions
            Text(
                stringResource(R.string.dimensions) + " (" + stringResource(R.string.inches) + ")",
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = uiState.lengthInches,
                    onValueChange = { viewModel.updateLength(it) },
                    label = { Text(stringResource(R.string.length)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                OutlinedTextField(
                    value = uiState.widthInches,
                    onValueChange = { viewModel.updateWidth(it) },
                    label = { Text(stringResource(R.string.width)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
                OutlinedTextField(
                    value = uiState.heightInches,
                    onValueChange = { viewModel.updateHeight(it) },
                    label = { Text(stringResource(R.string.height)) },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }

            Spacer(Modifier.height(12.dp))

            // Weight
            OutlinedTextField(
                value = uiState.weightOz,
                onValueChange = { viewModel.updateWeight(it) },
                label = { Text(stringResource(R.string.weight_oz)) },
                modifier = Modifier.fillMaxWidth(0.5f),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )

            Spacer(Modifier.height(16.dp))

            // Asking Price
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    if (uiState.suggestedPriceLow.isNotBlank()) {
                        Text(
                            "${stringResource(R.string.suggested_price)}: \$${uiState.suggestedPriceLow} - \$${uiState.suggestedPriceHigh}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                    OutlinedTextField(
                        value = uiState.askingPrice,
                        onValueChange = { viewModel.updateAskingPrice(it) },
                        label = { Text(stringResource(R.string.asking_price)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        leadingIcon = { Icon(Icons.Default.AttachMoney, contentDescription = null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Save button
            Button(
                onClick = { viewModel.saveListing(onSaved) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isSaving && uiState.title.isNotBlank()
            ) {
                if (uiState.isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(20.dp))
                }
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.save))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConditionDropdown(
    selectedCondition: String,
    onConditionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val conditions = listOf(
        "new" to stringResource(R.string.condition_new),
        "like_new" to stringResource(R.string.condition_like_new),
        "good" to stringResource(R.string.condition_good),
        "fair" to stringResource(R.string.condition_fair),
        "poor" to stringResource(R.string.condition_poor)
    )

    val displayText = conditions.firstOrNull { it.first == selectedCondition }?.second ?: ""

    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        OutlinedTextField(
            value = displayText,
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.condition)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            conditions.forEach { (value, label) ->
                DropdownMenuItem(
                    text = { Text(label) },
                    onClick = {
                        onConditionSelected(value)
                        expanded = false
                    }
                )
            }
        }
    }
}
