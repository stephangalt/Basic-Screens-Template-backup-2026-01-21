# Basic Screens Template

Multiple blank screens (pages)
with topnavbar and menu for navigating between pages.
Some sample code is applied to Screens 1-4 but only for demonstration purposes.
Settings, Help and About screens are also provided.

## 🚀 How to Use This Template (Preferred)
This repository is configured as a GitHub Template. 
The recommended way to start a new project is to click the green **"Use this template"** button on the main repository page.
Follow the steps in GitHub to create your own repo with a name of your choice.
This will create a brand new repository for you with a clean Git history, automatically handling many of the manual cleanup steps listed below. (Setting up git steps probably can be excluded; they should be automatically handled by doing this process).
Then create a new local project clone of this new repo using the new URL.

## 🚀 How to Use This Template (Alternate)
For developers who have cloned this repository manually (start a new local project with a new name, use the URL from the Clone button on GitHub), follow the checklist below to ensure your new project is configured correctly.

_______________________________
Objective.
Use an existing project as a template for starting a new project.

Note: see Appendix
++ Naming conventions
_______________________________
🧭 Naming the project (from original template name) during cloning.
Clone the project and rename it during initial save (IDE or
git clone https://github.com/user/repo.git new-name)

Or > Close Android Studio, rename folder from File Explorer,
Back in Studio - Reopen the project via Open-> Folder.

This does not change anything inside the project — only the directory name.

Cloning does not rename:
the Gradle root project name
the Android Studio project name
the package name
the applicationId
the Git remote name
the GitHub repository name
README.md (if present)

The remaining steps need to be done manually.
_______________________________
Renaming the project internally

2️⃣ Rename the Gradle root project
Edit settings.gradle.kts
This updates how Android Studio labels the project.

Change rootProject.name = "Old Name"
to rootProject.name = "New Name"

Sync Gradle.

4️⃣ Rename the package structure (Java/Kotlin namespace)
A. In Android Studio Project view:
Right‑click the package:
com.oldname.app → Refactor → Rename
Choose 'All Directories'.

B. Apply refactor
Studio updates:
Kotlin imports
Manifest package references
R class paths
BuildConfig paths

If this does not result in packages being renamed then:
Projects screen, Android view. Select 3dot menu /Appearance
Uncheck 'Compact Middle Packages'.
Repeat above process but rename each part separately
(oldappname > newappname, then oldorgname > neworgname, then com > whatever (com, org, etc)

Note: com.example will not be accepted for submission in the Google Play Store

5️⃣ More updates
Note -
Theme.kt has the Jetpack Compose Theme - eg AppTheme() - used after Compose UI starts running.
This is different from -
themes.xml - the Android XML Theme (used by OS before Compose code runs)

The two are separate, and the naming does not have to match, but for consistency, it's good practice to make them similar. This keeps your branding consistent.

See ++ Naming conventions

themes.xml
<style name="Theme.NewAppName" parent="android:Theme.Material.Light.NoActionBar" />

app/build.gradle.kts. AKA build.gradle.kts (Module :app)
Update the applicationId, change this to new name
defaultConfig {
    applicationId = "com.neworgname.newappname"
}

AndroidManifest.xml 
(At least 2) android:them references such as:
android:theme="@style/Theme.NewAppName">

strings.xml
<string name="app_name">New App Name</string>
<string name="app_name_styled">New App Name</string>

recommended:
In file theme.kt
Rename from 
AppTheme()
to 
NewAppNameTheme() (eg BasicScreensTest01Theme())
If renamed update everywhere it is called (usually only from MainActivity)

(Legacy advice - modern Android projects omit this; rely for namespace in build.gradle.kts)
Update the AndroidManifest package
Change to:
package="com.newname.app"

****************
6️⃣ Update any hardcoded package references
Android Studio’s Refactor → Rename handles most of this, but a global search is smart.

Search for: com.oldname (or whatever it was)
Look for old project name strings in:
comments
strings.xml
DI modules, Hilt, Koin, etc.

* clean up import statements to reflect change. (see global 'find and replace' command)

* check for old namespace in test packages

_______________________________
7️⃣ Clean + rebuild
Run: 
Build → Clean Project
Build → Assemble Project
This flushes stale R classes and BuildConfig.

_______________________________
8️⃣ Git updates !!!
The following will most likely not be needed if the project was generated from GitHub via the 'use this template' process. 

Start a clean Git history
Clean up git history (Cloned project will retain Git history from old_project)

A
Manage remotes
remove references to the prior (startup template) project!
/Git /Manage Remotes

B
Remove local old git history (.git folder from new project's root directory)
Powershell
rm .git  //remove the .git folder eg. D:\Data\android_projects\BasicScreens\.git
or
rm .git -r -Force 
or 
*️⃣Remove-Item .git -Recurse -Force 

Or, use Windows File explorer 
| Environment | Command to Recursively Delete the .git Directory | Explanation | | :--- | :--- | :--- | 
| Linux, macOS, Git Bash | rm -rf .git | rm (remove), -r (recursive), -f (force, ignore nonexistent files and never prompt). This is the standard Unix command. | 
| Windows PowerShell | Remove-Item -Recurse -Force .git | This is the native PowerShell cmdlet. It uses verbose, capitalized parameters which is typical for PowerShell. ri is a common alias. | 
| Windows Command Prompt (CMD) | rmdir /s /q .git | This is the command for the older Windows Command Prompt. rmdir (remove directory), /s (recursive), /q (quiet, no prompt). |

!! check .gitignore
see appendix +++ 

C ++++
Restart a new git history
Review .gitignore for best practices
git init //Initialize a new local .git folder
git add . //Add all newly renamed files to git tracking
git commit -m "Initial commit of NewProject"

D
Create a new remote repo 
in IDE (/Git /Manage Remotes) 
- remove any references to remotes for old_project 
- from web create a new repo, do not include a README or LICENSE files.
- copy URL and (in IDE) add to remotes list (/Git /Manage Remotes)


**********************************
Appendix

********************
++ Naming conventions
There are different variations of the project name in different locations.
Some locations disallow spaces or dashes, others have preferred naming conventions (eg PascalCase).

Sample -
During initial cloning/importing select the name 'Basic Screens Test01'

Windows folder will be created with name 
Basic-Screens-Test01

Apply these updates -
settings.gradle.kts 
rootProject.name = "Basic Screens Test01"
(This becomes the name displayed in the IDE)

themes.xml
<style name="Theme.BasicScreensTest01" parent="android:Theme.Material.Light.NoActionBar" />

build.gradle.kts (:app): 
applicationId = "org.smithbros.basic_screens_test01"

AndroidManifest.xml 
(At least 2) references such as:
android:theme="@style/Theme.BasicScreensTest01">

strings.xml
<string name="app_name">Basic Screens Test01</string>
<string name="app_name_styled">Basic Screens Test01</string>

recommended:
theme.kt. 
Rename AppTheme() to BasicScreensTest01Themme()
If renamed update where it is called (usually only MainActivity)

********************
+++ preferred .gitignore contents
# Standard Android Studio generated files
*.iml
.gradle
/local.properties

# =====================================================================
# IDE-specific files (IntelliJ, Android Studio)
#
# This is the modern, granular approach. It ignores user-specific files
# but allows shared settings like code styles to be committed.
# Shared Project Settings (SHOULD NOT be ignored):
#codeStyles/: This directory contains your project's specific code style and formatting rules. If you go to Settings > Editor > Code Style and choose to store your scheme as "Project," the settings are saved here.
#copyright/: Contains copyright profiles.
#runConfigurations/: Can contain shared run configurations that everyone on the team should have.
#dictionaries/: Contains custom dictionary words for the spell checker.

# Do not edit this section unless you know what you're doing.
.idea/caches/
.idea/gradle.xml
.idea/misc.xml
.idea/modules.xml
.idea/render.experimental.xml
.idea/vcs.xml
.idea/workspace.xml
.idea/navEditor.xml
# =====================================================================

.DS_Store
.externalNativeBuild
.cxx
/.gradle
/.kotlin

# Build artifacts and output
# This ignores the root build folder AND all module build folders.
build/

# Jetpack Compose compiler reports and metrics
/compose-metrics
/compose-reports

# Captures and profiling output
/captures
*.hprof

# Keystores and signing information - MUST NOT be committed
*.jks
*.keystore

# Other
Thumbs.db
ehthumbs.db
*.log
*.stacktrace
signing.properties

# Compiled application files
*.apk
*.aab

# Generated license report
/app/src/main/assets/open_source_licenses.html


********************
++++ Git setup issues
if IDE shows main but Terminal states it is on master.

1 Test for mismatches here.
compare paths and version numbers of git tool used by each:

In your IDE: Go to the Git settings (e.g., Settings > Version Control > Git) and see the path to the git.exe. You can often click a "Test" button to see the version.
In terminal session:
PS Get-Command git

# See the value for the default branch
git config --get init.defaultBranch

# See where this configuration is coming from (local, global, or system)
git config --show-origin --get init.defaultBranch


2 Fix
Try restarting Android Studio

or
Force terminal session to use main
# This command sets the default branch to 'main' for all new repositories
git config --global init.defaultBranch main

********************