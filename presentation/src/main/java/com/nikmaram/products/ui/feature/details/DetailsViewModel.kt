package com.nikmaram.products.ui.feature.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nikmaram.products.ui.base.BaseViewModel
import com.nikmaram.products.ui.navigation.Navigation.Args.PRODUCT_ID
import com.nikmaram.usecase.GetProductByIdUseCase
import com.nikmaram.usecase.UpdateBookMarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productByIdUseCase: GetProductByIdUseCase,
    private val updateBookMarkUseCase: UpdateBookMarkUseCase
) : BaseViewModel<DetailsContract.Event, DetailsContract.DetailsState, DetailsContract.Effect>() {
    private var productId = 0
    private val _isBookMarked = mutableStateOf(false)
    val isBookMarked: State<Boolean> get() = _isBookMarked
        init {
            savedStateHandle.get<Int>(PRODUCT_ID)?.let { productId ->
                this.productId = productId
                getProductDetails(productId)
            }
    }

    override fun setInitialState() = DetailsContract.DetailsState.Initial

    override fun handleEvents(event: DetailsContract.Event) {
        when (event) {
            DetailsContract.Event.BackButtonClicked -> {
                setEffect { DetailsContract.Effect.Navigation.Back }
            }
            DetailsContract.Event.Retry -> getProductDetails(id = productId)
            is DetailsContract.Event.BookMarkClicked -> {
                updateBookMark(id = productId, bookMarked = event.isBookMarked)
            }
        }
    }

    private fun getProductDetails(id:Int) {
        setState { DetailsContract.DetailsState.LoadingState }
        viewModelScope.launch {
            productByIdUseCase.invoke(id).onSuccess { data ->
                _isBookMarked.value = data?.isBookMarked ?: false
                setState { DetailsContract.DetailsState.DataLoaded(data) }
            }.onFailure {
                setState { DetailsContract.DetailsState.Error }
            }
        }
    }

    private fun updateBookMark(id:Int, bookMarked:Boolean) {
        _isBookMarked.value = bookMarked
        viewModelScope.launch {
            updateBookMarkUseCase.invoke(id, bookMarked)
        }
    }

}
