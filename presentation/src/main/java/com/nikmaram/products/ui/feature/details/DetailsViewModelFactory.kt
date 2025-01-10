package com.nikmaram.products.ui.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikmaram.usecase.GetProductByIdUseCase
import com.nikmaram.usecase.UpdateBookMarkUseCase
import javax.inject.Inject

class DetailsViewModelFactory @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val productByIdUseCase: GetProductByIdUseCase,
    private val updateProductUseCase: UpdateBookMarkUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(savedStateHandle, productByIdUseCase, updateProductUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 