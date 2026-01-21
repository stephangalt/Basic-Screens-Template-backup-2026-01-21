package org.smithbros.basicscreenstemplate.ui.screen

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import org.smithbros.basicscreenstemplate.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.smithbros.basicscreenstemplate.navigation.AppScreen
import org.smithbros.basicscreenstemplate.ui.HintDialog
import org.smithbros.basicscreenstemplate.ui.MenuItem
import org.smithbros.basicscreenstemplate.ui.StandardTopAppBar
import org.smithbros.basicscreenstemplate.ui.theme.AppTheme
import org.smithbros.basicscreenstemplate.ui.viewmodel.AppViewModel
import org.smithbros.basicscreenstemplate.ui.viewmodel.AppViewModelFactory

/**
 * The main home screen of the application.
 *
 * This screen serves as a template for a primary screen in the app. It features a
 * [StandardTopAppBar] with a hamburger menu for global navigation and a slot for
 * screen-specific actions (like the Info icon). The body of the screen is a simple
 * placeholder ready for application-specific content.
 *
 * @param navController The [NavController] used to handle all navigation events,
 *                      such as opening other screens from the hamburger menu.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen1(
    viewModel: AppViewModel = viewModel(factory = AppViewModelFactory()),
    navController: NavController
) {
    // This variable is intentionally unused in the template.
    // It is ready to be used when a developer adds screen-specific logic.
    @Suppress("UNUSED_VARIABLE")
    val uiState by viewModel.uiState.collectAsState()

    // For Info dialog
    var showHintDialog by remember { mutableStateOf(false) }

    // --- State for the Snackbar ---
    val snackbarHostState = remember { SnackbarHostState() }
    // Scope for launching the snackbar
    val scope = rememberCoroutineScope()

    // 1. Get the screen's title from the single source of truth (strings.xml) via AppScreen()
    val screenTitle = stringResource(id = AppScreen.SCREEN_1.titleResId)
    val appName = stringResource(id = R.string.app_name)

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
                actions = {
                    // Kept the Info icon as an example action for this screen
                    IconButton(onClick = { showHintDialog = true }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Show Hint"
                        )
                    }
                }
            ) {
                // 4. Use the resolved screen title for the visible app bar title.
                Text(
                    text = (screenTitle),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        // This Box now serves as the main content area.
        //TODO replace dummy content
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                // A placeholder for touch listeners like tap, swipe, etc.
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            // 3. EXAMPLE: Trigger the snackbar on tap
                            scope.launch {
                                snackbarHostState.showSnackbar("Snackbar triggered on tap!")
                                println("Screen was tapped")
                            }
                        }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                "This is the $screenTitle\nThe name of this application is:\n$appName\n\n"+
                        "'Basic Screens Template' is the project designed to be the template for this application.\n" +
                        "For more information see Help screen.\n\nTap the screen to see a snackbar/popup message",
                textAlign = TextAlign.Center
            )


            if (showHintDialog) {
                HintDialog(
                    onDismiss = { showHintDialog = false }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Screen1Preview() {
    AppTheme {
        // Dummy NavController for preview purposes
        val navController = rememberNavController()
        Screen1(navController = navController)
    }
}