package com.nikmaram.products.ui.home


import androidx.lifecycle.viewModelScope
import com.nikmaram.products.ui.base.BaseViewModel
import com.nikmaram.usecase.GetAllProductUseCase
import com.nikmaram.usecase.GetProductByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val allProductUseCase: GetAllProductUseCase,
    private val productByTitleUseCase: GetProductByTitleUseCase
) : BaseViewModel<HomeContract.Event, HomeContract.HomeState, HomeContract.Effect>() {

    init {
        getAllProduct()
    }

    override fun setInitialState() = HomeContract.HomeState.Initial

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.Retry -> getAllProduct()
            is HomeContract.Event.OnProductClicked -> setEffect {
                HomeContract.Effect.Navigation.ToDetails(event.product)
            }
        }
    }

    private fun getAllProduct() {
        setState { HomeContract.HomeState.LoadingState }
        viewModelScope.launch {
            allProductUseCase.invoke().onSuccess { data ->
                setState { HomeContract.HomeState.DataLoaded(data) }
            }.onFailure {
                setState { HomeContract.HomeState.Error }
            }
        }
    }
}
