package org.smithbros.basicscreenstemplatebackup20260121.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.smithbros.basicscreenstemplatebackup20260121.R
import org.smithbros.basicscreenstemplatebackup20260121.navigation.AppScreen
import org.smithbros.basicscreenstemplatebackup20260121.navigation.NavRoutes
import org.smithbros.basicscreenstemplatebackup20260121.ui.MenuItem
import org.smithbros.basicscreenstemplatebackup20260121.ui.StandardTopAppBar
import org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel.AppViewModel
import org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel.AppViewModelFactory
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign

/**
 * A screen that displays help and guidance information for the application.
 *
 * This screen is part of the main navigation flow and uses the [StandardTopAppBar]
 * with a hamburger menu. The actual help text displayed is fetched from a string
 * resource (`R.string.help_screen_content`), making it easy to update and translate.
 *
 * @param navController The [NavController] used to handle navigation actions,
 *                      such as returning to the home screen via the bottom button
 *                      or navigating via the hamburger menu.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelpScreen(
    viewModel: AppViewModel = viewModel(factory = AppViewModelFactory()),
    navController: NavController
) {
    // This variable is intentionally unused in the template.
    // It is ready to be used when a developer adds screen-specific logic.
    val uiState by viewModel.uiState.collectAsState()

    // 1. Get the screen's title from the single source of truth (strings.xml) via AppScreen()
    val screenTitle = stringResource(id = AppScreen.HELP.titleResId)

    Scaffold(
        topBar = {
            StandardTopAppBar(
                navController = navController,
                showHamburgerMenu = true,
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
            ) {
                // 4. Use the resolved screen title for the visible app bar title.
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
                Button(
                    onClick = { navController.navigate(NavRoutes.SCREEN_1) {
                        popUpTo(NavRoutes.SCREEN_1) { inclusive = true }
                    } },
                    shape = MaterialTheme.shapes.small,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(id = AppScreen.SCREEN_1.titleResId))
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.help_screen_content),
                textAlign = TextAlign.Center
            )
        }
    }
}
