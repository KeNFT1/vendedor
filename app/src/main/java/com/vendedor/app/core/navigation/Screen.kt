package com.vendedor.app.core.navigation

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object Camera : Screen("camera")
    data object PhotoReview : Screen("photo_review/{itemId}") {
        fun createRoute(itemId: Long) = "photo_review/$itemId"
    }
    data object Identification : Screen("identification/{itemId}") {
        fun createRoute(itemId: Long) = "identification/$itemId"
    }
    data object PriceComparison : Screen("price_comparison/{itemId}") {
        fun createRoute(itemId: Long) = "price_comparison/$itemId"
    }
    data object ListingForm : Screen("listing_form/{itemId}") {
        fun createRoute(itemId: Long) = "listing_form/$itemId"
    }
    data object ListingDetail : Screen("listing_detail/{itemId}") {
        fun createRoute(itemId: Long) = "listing_detail/$itemId"
    }
    data object ShippingInfo : Screen("shipping_info/{itemId}") {
        fun createRoute(itemId: Long) = "shipping_info/$itemId"
    }
    data object Publish : Screen("publish/{itemId}") {
        fun createRoute(itemId: Long) = "publish/$itemId"
    }
    data object Settings : Screen("settings")
}
