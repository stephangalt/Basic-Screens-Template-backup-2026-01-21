package org.smithbros.basicscreenstemplatebackup20260121.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * A placeholder UiState data class for the generic AppViewModel.
 *
 * A new project should expand this to hold the actual state needed
 * for its screens (e.g., user data, lists of items, loading status).
 */
data class AppUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

/**
 * A generic ViewModel to serve as a starting point for a new application.
 *
 * This ViewModel provides a basic UiState flow. A developer cloning this
 * template should rename this class and expand its UiState and functions
 * to fit their application's specific needs.
 */
class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AppUiState())
    val uiState = _uiState.asStateFlow()

    // TODO: Add functions here to modify the state, for example:
    /*
    fun loadData() {
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            // ... perform data loading ...
        }
    }
    */
}