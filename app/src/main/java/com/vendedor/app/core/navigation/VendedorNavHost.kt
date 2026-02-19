package com.vendedor.app.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vendedor.app.feature.camera.ui.CameraScreen
import com.vendedor.app.feature.identification.ui.IdentificationResultScreen
import com.vendedor.app.feature.listing.ui.HomeScreen
import com.vendedor.app.feature.listing.ui.ListingDetailScreen
import com.vendedor.app.feature.listing.ui.ListingFormScreen
import com.vendedor.app.feature.publishing.ui.PublishScreen
import com.vendedor.app.feature.settings.ui.SettingsScreen
import com.vendedor.app.feature.shipping.ui.ShippingInfoScreen

@Composable
fun VendedorNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        // Home
        composable(Screen.Home.route) {
            HomeScreen(
                onAddItem = {
                    navController.navigate(Screen.Camera.route)
                },
                onItemClick = { itemId ->
                    navController.navigate(Screen.ListingDetail.createRoute(itemId))
                },
                onSettings = {
                    navController.navigate(Screen.Settings.route)
                }
            )
        }

        // Camera
        composable(Screen.Camera.route) {
            CameraScreen(
                onClose = { navController.popBackStack() },
                onPhotosConfirmed = { itemId ->
                    navController.navigate(Screen.Identification.createRoute(itemId)) {
                        popUpTo(Screen.Home.route)
                    }
                }
            )
        }

        // AI Identification
        composable(
            route = Screen.Identification.route,
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) {
            IdentificationResultScreen(
                onBack = { navController.popBackStack() },
                onContinue = { itemId ->
                    navController.navigate(Screen.ListingForm.createRoute(itemId))
                }
            )
        }

        // Listing Form (create/edit)
        composable(
            route = Screen.ListingForm.route,
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) {
            ListingFormScreen(
                onBack = { navController.popBackStack() },
                onSaved = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                }
            )
        }

        // Listing Detail
        composable(
            route = Screen.ListingDetail.route,
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getLong("itemId") ?: 0L
            ListingDetailScreen(
                onBack = { navController.popBackStack() },
                onEdit = { id ->
                    navController.navigate(Screen.ListingForm.createRoute(id))
                },
                onShipping = { id ->
                    navController.navigate(Screen.ShippingInfo.createRoute(id))
                },
                onPublish = { id ->
                    navController.navigate(Screen.Publish.createRoute(id))
                }
            )
        }

        // Shipping Info (Pirateship)
        composable(
            route = Screen.ShippingInfo.route,
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) {
            ShippingInfoScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // Publish / Export
        composable(
            route = Screen.Publish.route,
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) {
            PublishScreen(
                onBack = { navController.popBackStack() }
            )
        }

        // Settings
        composable(Screen.Settings.route) {
            SettingsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
