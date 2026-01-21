package org.smithbros.basicscreenstemplatebackup20260121.ui.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.navigation.NavController
import org.smithbros.basicscreenstemplatebackup20260121.navigation.AppScreen
import org.smithbros.basicscreenstemplatebackup20260121.navigation.NavRoutes
import org.smithbros.basicscreenstemplatebackup20260121.ui.MenuItem
import org.smithbros.basicscreenstemplatebackup20260121.ui.StandardTopAppBar
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel.AppViewModel
import org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel.AppViewModelFactory

/**
 * A generic placeholder screen for the application.
 *
 * This screen serves as a template for a secondary screen in the app. It features a
 * [StandardTopAppBar] with a hamburger menu for global navigation. The body of the screen
 * is a simple placeholder ready for application-specific content.
 *
 * @param navController The [NavController] used to handle all navigation events,
 *                      such as opening other screens from the hamburger menu.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen3(
    viewModel: AppViewModel = viewModel(factory = AppViewModelFactory()),
    navController: NavController
) {
    // This variable is intentionally unused in the template.
    // It is ready to be used when a developer adds screen-specific logic.
    val uiState by viewModel.uiState.collectAsState()

    // 1. Get the screen's title from the single source of truth (strings.xml) via AppScreen()
    val screenTitle = stringResource(id = AppScreen.SCREEN_3.titleResId)

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
                Text(
                    // 4. Use the resolved screen title for the visible app bar title.
                    text = (screenTitle),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        bottomBar = {
            //TODO Decide whether to keep bottomBar and this dummy content
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
        // This Box now serves as the main content area.
        //TODO replace dummy content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Or LazyColumn() - whatever is needed
            Column(
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                /* This space left blank */
                //TODO add value here for this screen
            }
        }
    }
}
