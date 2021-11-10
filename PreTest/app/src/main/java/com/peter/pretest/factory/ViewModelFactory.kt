package com.peter.pretest.factory

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.peter.pretest.DemoViewModel
import com.peter.pretest.data.source.PretestRepository

/**
 *
 * Factory for ViewModels.
 */
class ViewModelFactory(
    private val pretestRepository: PretestRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DemoViewModel::class.java)) {
            return DemoViewModel(pretestRepository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

}