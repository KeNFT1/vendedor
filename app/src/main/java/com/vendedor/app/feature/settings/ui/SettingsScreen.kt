package com.vendedor.app.feature.settings.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.vendedor.app.R
import com.vendedor.app.feature.settings.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var currentLocale by remember {
        mutableStateOf(
            AppCompatDelegate.getApplicationLocales().toLanguageTags().ifEmpty { "es" }
        )
    }

    // API Keys
    var geminiKey by remember { mutableStateOf(viewModel.getGeminiKey()) }
    var ebayClientId by remember { mutableStateOf(viewModel.getEbayClientId()) }
    var ebaySecret by remember { mutableStateOf(viewModel.getEbaySecret()) }
    var showGeminiKey by remember { mutableStateOf(false) }
    var showEbaySecret by remember { mutableStateOf(false) }

    // Sender address
    var senderName by remember { mutableStateOf(viewModel.getSenderName()) }
    var senderAddress by remember { mutableStateOf(viewModel.getSenderAddress()) }
    var senderCity by remember { mutableStateOf(viewModel.getSenderCity()) }
    var senderState by remember { mutableStateOf(viewModel.getSenderState()) }
    var senderZip by remember { mutableStateOf(viewModel.getSenderZip()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.settings)) },
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
            // === API KEYS SECTION ===
            SectionHeader(icon = Icons.Default.Key, title = "API Keys")
            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Gemini API Key
                    OutlinedTextField(
                        value = geminiKey,
                        onValueChange = { geminiKey = it },
                        label = { Text("Gemini API Key") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = if (showGeminiKey) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { showGeminiKey = !showGeminiKey }) {
                                Icon(
                                    if (showGeminiKey) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Spacer(Modifier.height(12.dp))

                    // eBay Client ID
                    OutlinedTextField(
                        value = ebayClientId,
                        onValueChange = { ebayClientId = it },
                        label = { Text("eBay Client ID") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(Modifier.height(12.dp))

                    // eBay Client Secret
                    OutlinedTextField(
                        value = ebaySecret,
                        onValueChange = { ebaySecret = it },
                        label = { Text("eBay Client Secret") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = if (showEbaySecret) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { showEbaySecret = !showEbaySecret }) {
                                Icon(
                                    if (showEbaySecret) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    Spacer(Modifier.height(12.dp))

                    Button(
                        onClick = {
                            viewModel.saveApiKeys(geminiKey, ebayClientId, ebaySecret)
                            scope.launch { snackbarHostState.showSnackbar("API keys saved") }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.save))
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // === SENDER ADDRESS SECTION ===
            SectionHeader(icon = Icons.Default.LocalShipping, title = stringResource(R.string.sender_address))
            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = senderName,
                        onValueChange = { senderName = it },
                        label = { Text(stringResource(R.string.address_name)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = senderAddress,
                        onValueChange = { senderAddress = it },
                        label = { Text(stringResource(R.string.address_street)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(Modifier.height(8.dp))
                    Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = senderCity,
                            onValueChange = { senderCity = it },
                            label = { Text(stringResource(R.string.address_city)) },
                            modifier = Modifier.weight(2f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = senderState,
                            onValueChange = { senderState = it },
                            label = { Text(stringResource(R.string.address_state)) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = senderZip,
                            onValueChange = { senderZip = it },
                            label = { Text(stringResource(R.string.address_zip)) },
                            modifier = Modifier.weight(1.2f),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = {
                            viewModel.saveSenderAddress(senderName, senderAddress, senderCity, senderState, senderZip)
                            scope.launch { snackbarHostState.showSnackbar("Address saved") }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Save, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.save_address))
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            // === LANGUAGE SECTION ===
            SectionHeader(icon = Icons.Default.Language, title = stringResource(R.string.language))
            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column {
                    LanguageOption(
                        label = stringResource(R.string.spanish),
                        isSelected = currentLocale.startsWith("es") || currentLocale.isEmpty(),
                        onClick = {
                            currentLocale = "es"
                            AppCompatDelegate.setApplicationLocales(
                                LocaleListCompat.forLanguageTags("es")
                            )
                        }
                    )
                    HorizontalDivider()
                    LanguageOption(
                        label = stringResource(R.string.english),
                        isSelected = currentLocale.startsWith("en"),
                        onClick = {
                            currentLocale = "en"
                            AppCompatDelegate.setApplicationLocales(
                                LocaleListCompat.forLanguageTags("en")
                            )
                        }
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // === ABOUT SECTION ===
            Text(stringResource(R.string.about), style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(stringResource(R.string.version), style = MaterialTheme.typography.bodyMedium)
                    Spacer(Modifier.weight(1f))
                    Text("1.0.0", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun SectionHeader(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(20.dp), tint = MaterialTheme.colorScheme.primary)
        Spacer(Modifier.width(8.dp))
        Text(title, style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
private fun LanguageOption(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.Language,
            contentDescription = null,
            tint = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.width(12.dp))
        Text(label, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        if (isSelected) {
            Icon(Icons.Default.Check, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        }
    }
}
