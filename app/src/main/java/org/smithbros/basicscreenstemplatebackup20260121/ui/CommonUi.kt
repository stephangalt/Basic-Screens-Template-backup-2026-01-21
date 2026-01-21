package org.smithbros.basicscreenstemplatebackup20260121.ui

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController

/**
 * A helper extension function to robustly find the underlying [Activity] from a [Context].
 *
 * In Jetpack Compose, `LocalContext.current` can sometimes provide a `ContextWrapper`
 * instead of the `Activity` itself. This function safely unwraps the context recursively
 * until it finds the base `Activity` or returns `null` if no activity can be found.
 *
 * @return The base [Activity] if found, otherwise `null`.
 */
fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}


/**
 * A generic data class representing a single item in a dropdown menu.
 *
 * This class is used to populate the menu in the [StandardTopAppBar]. It decouples the
 * app bar from the application's specific navigation logic by providing a simple,
 * "dumb" structure containing only display text and a click action.
 *
 * @param title The user-facing text to be displayed for this menu item.
 * @param onClick A lambda function to be executed when this menu item is clicked.
 */
data class MenuItem(
    val title: String,
    val onClick: () -> Unit
)

/**
 * A standardized, configurable TopAppBar for the application.
 *
 * This composable is a "dumb component". It only knows how to display UI.
 * The calling screen is responsible for providing the data (e.g., menu items)
 * and the logic (e.g., onClick actions).
 *
 * @param title The composable content to display as the screen's title.
 * @param navController The [NavController] for handling back navigation.
 * @param activeScreenTitleForMenu The title of the current screen, to disable the correct item in the menu. (Only needed when showHamburgerMenu == true )
 * @param menuItems The list of generic [MenuItem]s to display in the hamburger dropdown menu.
 * @param showBackArrow Whether to display the back navigation arrow.
 * @param onBackClick A custom lambda for the back arrow. Defaults to `navController.navigateUp()`.
 * @param showHamburgerMenu Whether to display the hamburger menu for global navigation.
 * @param showOverflowMenu Whether to display the standard overflow menu for contextual actions.
 * @param actions A slot for adding custom composable actions (e.g., `IconButton`s) to the top bar.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StandardTopAppBar(
    navController: NavController,
    activeScreenTitleForMenu: String? = null,
    menuItems: List<MenuItem> = emptyList(), // <-- NEW: Accepts a list of generic items
    showBackArrow: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    showHamburgerMenu: Boolean = false,
    showOverflowMenu: Boolean = false,
    actions: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit,
) {
    var showNavMenu by remember { mutableStateOf(false) }
    var showContextMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = title,
        navigationIcon = {
            if (showHamburgerMenu) {
                Box {
                    IconButton(onClick = { showNavMenu = true }) {
                        Icon(Icons.Default.Menu, contentDescription = "Navigation Menu")
                    }
                    DropdownMenu(
                        expanded = showNavMenu,
                        onDismissRequest = { showNavMenu = false }
                    ) {
                        // REFACTORED: Loop over the list provided by the app
                        menuItems.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item.title) },
                                onClick = {
                                    showNavMenu = false
                                    // Just execute the action the app provided
                                    item.onClick()
                                },
                                enabled = activeScreenTitleForMenu != item.title,
                                colors = MenuDefaults.itemColors(textColor = colorScheme.onSurfaceVariant)
                            )
                        }
                    }
                }
            } else if (showBackArrow) {
                IconButton(onClick = { onBackClick?.invoke() ?: navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            actions()
            if (showOverflowMenu) {
                Box {
                    IconButton(onClick = { showContextMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                    }
                    DropdownMenu(
                        expanded = showContextMenu,
                        onDismissRequest = { showContextMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("< --- >") },
                            onClick = {},
                            enabled = false,
                            colors = MenuDefaults.itemColors(textColor = colorScheme.onSurfaceVariant)
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorScheme.surfaceContainer,
            scrolledContainerColor = colorScheme.surfaceContainer,
            navigationIconContentColor = colorScheme.onSurface,
            titleContentColor = colorScheme.onSurface,
            actionIconContentColor = colorScheme.onSurface
        )
    )
}