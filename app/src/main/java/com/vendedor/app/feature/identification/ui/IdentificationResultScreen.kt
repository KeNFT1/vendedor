package com.vendedor.app.feature.identification.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.ContentCopy
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.vendedor.app.R
import com.vendedor.app.feature.identification.viewmodel.IdentificationViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdentificationResultScreen(
    onBack: () -> Unit,
    onContinue: (Long) -> Unit,
    viewModel: IdentificationViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val copiedText = stringResource(R.string.copied)

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.identify_item)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.cancel))
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Primary photo preview
            uiState.primaryPhotoPath?.let { path ->
                AsyncImage(
                    model = File(path),
                    contentDescription = stringResource(R.string.main_photo),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(Modifier.height(16.dp))
            }

            // Identify button
            if (uiState.identification == null) {
                Button(
                    onClick = { viewModel.identifyItem() },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = !uiState.isIdentifying && uiState.primaryPhotoPath != null
                ) {
                    if (uiState.isIdentifying) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = MaterialTheme.colorScheme.onPrimary,
                            strokeWidth = 2.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.identifying))
                    } else {
                        Icon(Icons.Default.AutoAwesome, contentDescription = null, modifier = Modifier.size(20.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.identify_item))
                    }
                }
                return@Column
            }

            // Editable fields after identification
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                )
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        stringResource(R.string.identification_complete),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Suggested price range
            if (uiState.suggestedPriceLow.isNotBlank() && uiState.suggestedPriceHigh.isNotBlank()) {
                Spacer(Modifier.height(8.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 12.dp, top = 4.dp, bottom = 4.dp, end = 4.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Default.AttachMoney,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.tertiary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(
                                "\$${uiState.suggestedPriceLow} - \$${uiState.suggestedPriceHigh}",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        }
                        IconButton(onClick = {
                            copyToClipboard(context, "\$${uiState.suggestedPriceLow} - \$${uiState.suggestedPriceHigh}", copiedText)
                        }) {
                            Icon(
                                Icons.Default.ContentCopy,
                                contentDescription = stringResource(R.string.copy),
                                modifier = Modifier.size(18.dp),
                                tint = MaterialTheme.colorScheme.tertiary
                            )
                        }
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Title
            CopyableTextField(
                value = uiState.title,
                onValueChange = { viewModel.updateTitle(it) },
                label = stringResource(R.string.title),
                context = context,
                copiedText = copiedText,
                singleLine = true
            )

            Spacer(Modifier.height(12.dp))

            // Description
            CopyableTextField(
                value = uiState.description,
                onValueChange = { viewModel.updateDescription(it) },
                label = stringResource(R.string.description),
                context = context,
                copiedText = copiedText,
                minLines = 3,
                maxLines = 6
            )

            Spacer(Modifier.height(12.dp))

            // Category & Brand
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                CopyableTextField(
                    value = uiState.category,
                    onValueChange = { viewModel.updateCategory(it) },
                    label = stringResource(R.string.category),
                    context = context,
                    copiedText = copiedText,
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                CopyableTextField(
                    value = uiState.brand,
                    onValueChange = { viewModel.updateBrand(it) },
                    label = stringResource(R.string.brand),
                    context = context,
                    copiedText = copiedText,
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }

            Spacer(Modifier.height(12.dp))

            // Condition dropdown with copy
            CopyableConditionDropdown(
                selectedCondition = uiState.condition,
                onConditionSelected = { viewModel.updateCondition(it) },
                context = context,
                copiedText = copiedText
            )

            Spacer(Modifier.height(12.dp))

            // Dimensions
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    stringResource(R.string.dimensions) + " (" + stringResource(R.string.inches) + ")",
                    style = MaterialTheme.typography.labelLarge
                )
                if (uiState.lengthInches.isNotBlank()) {
                    IconButton(onClick = {
                        copyToClipboard(
                            context,
                            "${uiState.lengthInches} x ${uiState.widthInches} x ${uiState.heightInches} in",
                            copiedText
                        )
                    }) {
                        Icon(
                            Icons.Default.ContentCopy,
                            contentDescription = stringResource(R.string.copy),
                            modifier = Modifier.size(18.dp),
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
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
            CopyableTextField(
                value = uiState.weightOz,
                onValueChange = { viewModel.updateWeight(it) },
                label = stringResource(R.string.weight_oz),
                context = context,
                copiedText = copiedText,
                modifier = Modifier.fillMaxWidth(0.5f),
                singleLine = true,
                keyboardType = KeyboardType.Decimal
            )

            Spacer(Modifier.height(24.dp))

            // Action buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = { viewModel.identifyItem() },
                    modifier = Modifier.weight(1f),
                    enabled = !uiState.isIdentifying
                ) {
                    Text(stringResource(R.string.retry))
                }
                Button(
                    onClick = {
                        viewModel.saveAndContinue {
                            onContinue(uiState.itemId)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.research_prices))
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
                }
            }
        }
    }
}

@Composable
private fun CopyableTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    context: Context,
    copiedText: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false,
    minLines: Int = 1,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.then(if (modifier == Modifier) Modifier.fillMaxWidth() else Modifier),
        singleLine = singleLine,
        minLines = if (singleLine) 1 else minLines,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = {
            if (value.isNotBlank()) {
                IconButton(onClick = { copyToClipboard(context, value, copiedText) }) {
                    Icon(
                        Icons.Default.ContentCopy,
                        contentDescription = stringResource(R.string.copy),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CopyableConditionDropdown(
    selectedCondition: String,
    onConditionSelected: (String) -> Unit,
    context: Context,
    copiedText: String
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

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = displayText,
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.condition)) },
            trailingIcon = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (displayText.isNotBlank()) {
                        IconButton(onClick = { copyToClipboard(context, displayText, copiedText) }) {
                            Icon(
                                Icons.Default.ContentCopy,
                                contentDescription = stringResource(R.string.copy),
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
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

private fun copyToClipboard(context: Context, text: String, toastMessage: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboard.setPrimaryClip(ClipData.newPlainText("Vendedor", text))
    Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
}
