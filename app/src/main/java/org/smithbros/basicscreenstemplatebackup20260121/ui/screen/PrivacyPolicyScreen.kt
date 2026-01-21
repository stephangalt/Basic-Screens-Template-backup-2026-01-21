package org.smithbros.basicscreenstemplatebackup20260121.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.smithbros.basicscreenstemplatebackup20260121.R
import org.smithbros.basicscreenstemplatebackup20260121.navigation.AppScreen
import org.smithbros.basicscreenstemplatebackup20260121.ui.MenuItem
import org.smithbros.basicscreenstemplatebackup20260121.ui.StandardTopAppBar
import org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel.AppViewModel
import org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel.AppViewModelFactory
import androidx.compose.runtime.getValue

/**
 * A detail screen that displays the application's privacy policy.
 *
 * This screen is designed to be navigated to from other screens (e.g., [AboutScreen])
 * and is not part of the primary navigation menu. It uses the [StandardTopAppBar]
 * configured with a back arrow to allow the user to return to the previous screen.
 * The content is fetched from a string resource (`R.string.privacypolicy_screen_content`).
 *
 * @param navController The [NavController] used for handling back navigation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyPolicyScreen(
    viewModel: AppViewModel = viewModel(factory = AppViewModelFactory()),
    navController: NavController

) {

    // This variable is intentionally unused in the template.
    // It is ready to be used when a developer adds screen-specific logic.
    val uiState by viewModel.uiState.collectAsState()

    // 1. This screen is a detail screen so name is not part of enum AppScreen()
    // Get the title directly from string resources.
    val screenTitle = stringResource(id = R.string.title_privacy_policy)


    Scaffold(
        topBar = {
            StandardTopAppBar(
                navController = navController,
                showHamburgerMenu = false,
                showBackArrow = true,
                // 2. Use the resolved screen title to identify the active menu item.
                activeScreenTitleForMenu = screenTitle,
                // 3. Build the list of menu items using the AppScreen enum.
                menuItems = AppScreen.entries.map { appScreen ->
                    MenuItem(
                        // Resolve the string resource for each menu item
                        title = stringResource(id = appScreen.titleResId),
                        onClick = { navController.navigate(appScreen.route) }
                    )
                },
                // The 'onBackClick' parameter is removed because the default behavior
                // (navController.navigateUp()) is exactly what's needed
                //onBackClick = { navController.popBackStack() } // Navigate back to AboutScreen
            ) {
                Text(screenTitle)
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(id = R.string.privacypolicy_screen_content)
            )
        }
    }
}
