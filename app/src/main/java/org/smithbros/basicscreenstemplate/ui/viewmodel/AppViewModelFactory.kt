package org.smithbros.basicscreenstemplate.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * A generic ViewModelFactory for creating instances of AppViewModel.
 *
 * This factory can be expanded to accept repositories or other dependencies
 * that the AppViewModel will need to perform its functions.
 */
class AppViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AppViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AppViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}