package org.smithbros.basicscreenstemplatebackup20260121

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.smithbros.basicscreenstemplatebackup20260121.navigation.NavRoutes
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.AboutScreen
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.AppLicenseInfo
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.GenericLicensesScreen
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.HelpScreen
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.PrivacyPolicyScreen
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.Screen1
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.Screen2
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.Screen3
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.Screen4
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.SettingsScreen
import org.smithbros.basicscreenstemplatebackup20260121.ui.screen.TermsOfServiceScreen
import org.smithbros.basicscreenstemplatebackup20260121.ui.theme.AppTheme
import androidx.lifecycle.ViewModelProvider
import org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel.AppViewModel
import org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel.AppViewModelFactory

class MainActivity : ComponentActivity()  {


    private lateinit var appViewModel: AppViewModel // New ViewModel instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize the generic ViewModel
        val appViewModelFactory = AppViewModelFactory()
        appViewModel = ViewModelProvider(this, appViewModelFactory)[AppViewModel::class.java]

        enableEdgeToEdge()
                setContent {
    
            val navController = rememberNavController()
    
    
            //AppTheme wraps around NavHost wraps around composables wraps around objects
            // Values from outer wrappers are communicated down to objects.
            AppTheme {
                Box(modifier = Modifier.navigationBarsPadding()) {
                    NavHost(navController = navController, startDestination = NavRoutes.SCREEN_1) {

                        composable(NavRoutes.SCREEN_1) { Screen1(navController = navController,viewModel = appViewModel)}
                        composable(NavRoutes.SCREEN_2) { Screen2(navController = navController, viewModel = appViewModel) }
                        composable(NavRoutes.SCREEN_3) { Screen3(navController = navController, viewModel = appViewModel) }
                        composable(NavRoutes.SCREEN_4) { Screen4(navController = navController, viewModel = appViewModel) }
                        composable(NavRoutes.SETTINGS) { SettingsScreen(navController = navController, viewModel = appViewModel) }
                        composable(NavRoutes.ABOUT) { AboutScreen(navController = navController, viewModel = appViewModel) }
                        composable(NavRoutes.HELP) { HelpScreen(navController = navController , viewModel = appViewModel) }
                        composable(NavRoutes.TERMS_OF_SERVICE) { TermsOfServiceScreen(navController = navController, viewModel = appViewModel) }
                        composable(NavRoutes.PRIVACY_POLICY) { PrivacyPolicyScreen(navController = navController, viewModel = appViewModel) }
    
                        // THIS IS THE GENERIC LICENSES SCREEN
                        composable(NavRoutes.LICENSES) {
                            // 1. Define the app-specific license information.
                            val appLicenseInfo = AppLicenseInfo(
                                appName = stringResource(id = R.string.app_name),
                                licenseContent = stringResource(id = R.string.app_license_content)
                            )
                            // 2. Call the generic, reusable screen from your UI layer.
                            GenericLicensesScreen(
                                navController = navController,
                                screenTitle = stringResource(id = R.string.title_licenses),
                                appLicenseInfo = appLicenseInfo
                            )
                        }
                    } // End NavHost
                }
            } // End of content (last parameter passed to AppTheme()
        }
        }

    override fun onDestroy() {
    super.onDestroy()
    }

} // End of class
