package org.smithbros.basicscreenstemplatebackup20260121.navigation

import androidx.annotation.StringRes
import org.smithbros.basicscreenstemplatebackup20260121.R

/**
 * Defines the unique routes for each destination in the app.
 * These are the constant strings used by the NavController.
 */
object NavRoutes {
    const val SCREEN_1 = "screen_1"
    const val SCREEN_2 = "screen_2"
    const val SCREEN_3 = "screen_3"
    const val SCREEN_4 = "screen_4"
    const val SETTINGS = "settings"
    const val HELP = "help"
    const val ABOUT = "about"
    const val LICENSES = "licenses_screen"
    const val TERMS_OF_SERVICE = "terms_of_service_screen"
    const val PRIVACY_POLICY = "privacy_policy_screen"
}

/**
 * Represents the screens that are available in the main navigation menu.
 * This app-specific enum connects a String Resource ID (for the translatable title)
 * to a stable NavRoute constant.
 */
enum class AppScreen(@StringRes val titleResId: Int, val route: String) {
    SCREEN_1(R.string.title_screen_1, NavRoutes.SCREEN_1),
    SCREEN_2(R.string.title_screen_2, NavRoutes.SCREEN_2),
    SCREEN_3(R.string.title_screen_3, NavRoutes.SCREEN_3),
    SCREEN_4(R.string.title_screen_4, NavRoutes.SCREEN_4),
    SETTINGS(R.string.title_settings, NavRoutes.SETTINGS),
    HELP(R.string.title_help, NavRoutes.HELP),
    ABOUT(R.string.title_about, NavRoutes.ABOUT)

}