package org.smithbros.basicscreenstemplatebackup20260121.ui.screen

import android.webkit.WebView
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import org.smithbros.basicscreenstemplatebackup20260121.ui.StandardTopAppBar
import java.io.IOException


/**
 * A data class representing the specific license information for the application
 * that is consuming this generic screen. This object is provided by the final app.
 *
 * @param appName The user-facing name of the application (e.g., "Basic Screens").
 * @param licenseContent The full text of the application's own license (e.g., MIT, Apache 2.0).
 * @param licenseTitle The title of the application's license (e.g., "MIT License").
 */
data class AppLicenseInfo(
    val appName: String,
    val licenseContent: String,
    val licenseTitle: String = "Insert License Name here" // Default can be overridden
)


/**
 * A generic, reusable screen to display open-source licenses for an application.
 *
 * This component is designed to be "dumb" and reusable. It constructs a themed HTML page
 * by combining app-specific license information with a generated third-party license
 * report. The final app is responsible for providing its own license details via the
 * [appLicenseInfo] parameter and ensuring the generated asset file exists.
 *
 * It uses a [WebView] to render the final HTML content.
 *
 * @param navController The [NavController] used for handling back navigation.
 * @param screenTitle The title to display in the [StandardTopAppBar].
 * @param appLicenseInfo An instance of [AppLicenseInfo] containing the consuming app's specific license details.
 * @param assetFileName The filename of the auto-generated open-source license report
 *                      located in the final app's `assets` folder.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericLicensesScreen(
    navController: NavController,
    screenTitle: String,
    appLicenseInfo: AppLicenseInfo,
    assetFileName: String = "open_source_licenses.html" // Provide a sensible default
) {
    val colors = MaterialTheme.colorScheme
    val backgroundColorHex = String.format("#%06X", (0xFFFFFF and colors.background.toArgb()))
    val textColorHex = String.format("#%06X", (0xFFFFFF and colors.onBackground.toArgb()))
    val context = LocalContext.current

    // Build the app-specific license HTML using data passed from the app
    val appLicenseHtml = """
        <div style="font-size: 1.2em; font-weight: bold; margin-top: 1em; padding-bottom: 0.5em; border-bottom: 1px solid #aaa;">Application License</div>
        <div style="margin-left: 1em; padding: 1em; border: 1px solid #ccc; white-space: pre-wrap; font-family: monospace;">
            <div style="font-weight: bold;">${appLicenseInfo.appName}<br>(${appLicenseInfo.licenseTitle})</div>
            <pre style="white-space: pre-wrap; margin-top: 0.5em;">${appLicenseInfo.licenseContent}</pre>
        </div>
        <div style="font-size: 1.2em; font-weight: bold; margin-top: 2em; padding-bottom: 0.5em; border-bottom: 1px solid #aaa;">Third-Party Open Source Licenses</div>
        <br>
    """.trimIndent()

    val themedHtml = try {
        val rawHtml = context.assets.open(assetFileName).bufferedReader().use { it.readText() }
        val style = """
            <style>
                body { background-color: $backgroundColorHex !important; color: $textColorHex !important; }
                a { color: $textColorHex !important; }
            </style>
        """.trimIndent()

        var result = rawHtml.replace("</head>", "$style</head>")
        if (result == rawHtml) result = rawHtml.replace("<body>", "<body>$style")

        result = result.replaceFirst("<body>", "<body>$appLicenseHtml")
        result.replaceFirst("<h1>Open Source Licenses</h1>", "").replaceFirst("<h2>Open Source Licenses</h2>", "")
    } catch (e: IOException) {
        "<html><body style=\"background-color:$backgroundColorHex;color:$textColorHex;\"><h1>Error</h1><p>Failed to load license information.</p>$appLicenseHtml</body></html>"
    }

    Scaffold(
        topBar = {
            StandardTopAppBar(navController = navController, showBackArrow = true) {
                Text(
                    text = (screenTitle),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    ) { innerPadding ->
        AndroidView(
            modifier = Modifier.padding(innerPadding),
            factory = { WebView(it) }
        ) {
            it.loadDataWithBaseURL(null, themedHtml, "text/html", "UTF-8", null)
        }
    }
}
