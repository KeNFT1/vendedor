package com.vendedor.app.feature.shipping.ui

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vendedor.app.R
import com.vendedor.app.feature.shipping.viewmodel.ShippingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShippingInfoScreen(
    onBack: () -> Unit,
    viewModel: ShippingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val copiedText = stringResource(R.string.copied)

    LaunchedEffect(uiState.copiedField) {
        uiState.copiedField?.let {
            snackbarHostState.showSnackbar(copiedText)
            viewModel.clearCopiedField()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.shipping_info)) },
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
            // Package Dimensions Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            stringResource(R.string.package_dimensions),
                            style = MaterialTheme.typography.titleMedium
                        )
                        IconButton(onClick = { viewModel.copyDimensions() }) {
                            Icon(
                                Icons.Default.ContentCopy,
                                contentDescription = stringResource(R.string.copy_dimensions),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

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
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            suffix = { Text("in") }
                        )
                        OutlinedTextField(
                            value = uiState.widthInches,
                            onValueChange = { viewModel.updateWidth(it) },
                            label = { Text(stringResource(R.string.width)) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            suffix = { Text("in") }
                        )
                        OutlinedTextField(
                            value = uiState.heightInches,
                            onValueChange = { viewModel.updateHeight(it) },
                            label = { Text(stringResource(R.string.height)) },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            suffix = { Text("in") }
                        )
                    }

                    Spacer(Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            stringResource(R.string.package_weight),
                            style = MaterialTheme.typography.titleMedium
                        )
                        IconButton(onClick = { viewModel.copyWeight() }) {
                            Icon(
                                Icons.Default.ContentCopy,
                                contentDescription = stringResource(R.string.copy_weight),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    OutlinedTextField(
                        value = uiState.weightOz,
                        onValueChange = { viewModel.updateWeight(it) },
                        label = { Text(stringResource(R.string.weight_oz)) },
                        modifier = Modifier.fillMaxWidth(0.5f),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                        suffix = { Text("oz") }
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Ship-to Address Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            stringResource(R.string.ship_to),
                            style = MaterialTheme.typography.titleMedium
                        )
                        IconButton(onClick = { viewModel.copyAddress() }) {
                            Icon(
                                Icons.Default.ContentCopy,
                                contentDescription = stringResource(R.string.copy_address),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    OutlinedTextField(
                        value = uiState.shipToName,
                        onValueChange = { viewModel.updateShipToName(it) },
                        label = { Text(stringResource(R.string.address_name)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = uiState.shipToAddress,
                        onValueChange = { viewModel.updateShipToAddress(it) },
                        label = { Text(stringResource(R.string.address_street)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = uiState.shipToCity,
                            onValueChange = { viewModel.updateShipToCity(it) },
                            label = { Text(stringResource(R.string.address_city)) },
                            modifier = Modifier.weight(2f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = uiState.shipToState,
                            onValueChange = { viewModel.updateShipToState(it) },
                            label = { Text(stringResource(R.string.address_state)) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = uiState.shipToZip,
                            onValueChange = { viewModel.updateShipToZip(it) },
                            label = { Text(stringResource(R.string.address_zip)) },
                            modifier = Modifier.weight(1.2f),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Tracking number
            OutlinedTextField(
                value = uiState.trackingNumber,
                onValueChange = { viewModel.updateTrackingNumber(it) },
                label = { Text(stringResource(R.string.tracking_number)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(Modifier.height(24.dp))

            // Save button
            Button(
                onClick = { viewModel.saveShippingInfo() },
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isSaving
            ) {
                Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.save))
            }

            Spacer(Modifier.height(8.dp))

            // Open Pirateship button
            Button(
                onClick = { viewModel.openPirateship() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Icon(Icons.Default.OpenInBrowser, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.open_pirateship))
            }
        }
    }
}
