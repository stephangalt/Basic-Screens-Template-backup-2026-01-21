package org.smithbros.basicscreenstemplatebackup20260121.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.smithbros.basicscreenstemplatebackup20260121.BuildConfig
import org.smithbros.basicscreenstemplatebackup20260121.R
import org.smithbros.basicscreenstemplatebackup20260121.navigation.AppScreen
import org.smithbros.basicscreenstemplatebackup20260121.navigation.NavRoutes
import org.smithbros.basicscreenstemplatebackup20260121.ui.MenuItem
import org.smithbros.basicscreenstemplatebackup20260121.ui.StandardTopAppBar
import org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel.AppViewModel
import org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel.AppViewModelFactory


/**
 * A screen that displays information about the application, including its name,
 * version, and developer. It also provides navigation links to legal documents
 * like the Privacy Policy, Terms of Service, and Open Source Licenses.
 *
 * This screen is part of the main navigation flow and uses the [StandardTopAppBar]
 * with a hamburger menu.
 *
 * @param navController The [NavController] used to handle navigation actions,
 *                      such as opening detail screens (e.g., Privacy Policy) or
 *                      navigating back to the home screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(

    viewModel: AppViewModel = viewModel(factory = AppViewModelFactory()),
    navController: NavController

) {
    // This variable is intentionally unused in the template.
    // It is ready to be used when a developer adds screen-specific logic.
    val uiState by viewModel.uiState.collectAsState()

    // 1. Get the screen's title from the single source of truth (strings.xml) via AppScreen()
    val screenTitle = stringResource(id = AppScreen.ABOUT.titleResId)

    Scaffold(
        topBar = {
            StandardTopAppBar(
                navController = navController,
                showHamburgerMenu = true,
                // 2. Use the resolved screen title to identify the active menu item.
                activeScreenTitleForMenu = screenTitle,
                menuItems = AppScreen.entries.map { appScreen ->
                    MenuItem(
                        // Resolve the string resource for each menu item
                        title = stringResource(id = appScreen.titleResId),
                        onClick = { navController.navigate(appScreen.route) }
                    )
                },
            ) {
                Text(
                    text = screenTitle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                //TODO decide whether to keep this button
                Button(
                    onClick = { navController.navigate(NavRoutes.SCREEN_1) {
                        popUpTo(NavRoutes.SCREEN_1) { inclusive = true }
                    } },
                    shape = MaterialTheme.shapes.small, // Enforce theme shape
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Done")
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${stringResource(id = R.string.app_name)}\n\nVersion ${BuildConfig.VERSION_NAME}\n\nDeveloped by Smith Bros",
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(32.dp))

                OutlinedButton(onClick = { navController.navigate(NavRoutes.PRIVACY_POLICY) }) {
                    Text(stringResource(id = R.string.title_privacy_policy))
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(onClick = { navController.navigate(NavRoutes.TERMS_OF_SERVICE) }) {
                    Text(stringResource(id = R.string.title_tos))
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(onClick = { navController.navigate(NavRoutes.LICENSES) }) {
                    Text(stringResource(id = R.string.title_licenses))
                }
            }
        }
    }
}
