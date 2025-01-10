package com.nikmaram.products.ui.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.nikmaram.products.ui.base.BaseViewModel
import com.nikmaram.products.ui.home.HomeContract
import com.nikmaram.products.ui.navigation.Navigation.Args.PRODUCT_ID
import com.nikmaram.usecase.GetProductByIdUseCase
import com.nikmaram.usecase.UpdateProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val productByIdUseCase: GetProductByIdUseCase,
    private val updateProductUseCase: UpdateProductUseCase
) : BaseViewModel<DetailsContract.Event, DetailsContract.DetailsState, DetailsContract.Effect>() {
    private var productId = 0
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
        }
    }

    private fun getProductDetails(id:Int) {
        setState { DetailsContract.DetailsState.LoadingState }
        viewModelScope.launch {
            productByIdUseCase.invoke(id).onSuccess { data ->
                setState { DetailsContract.DetailsState.DataLoaded(data) }
            }.onFailure {
                setState { DetailsContract.DetailsState.Error }
            }
        }
    }


}
