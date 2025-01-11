package com.nikmaram.products.ui.feature.home


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> get() = _searchQuery

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
            is HomeContract.Event.OnSearchQueryChanged -> {
                _searchQuery.value = event.query ?: ""
                event.query?.let { searchProduct(it) }
            }
        }
    }

    private fun searchProduct(query: String) {
        setState { HomeContract.HomeState.LoadingState }
        viewModelScope.launch {
            val searchQuery = "%$query%"
            productByTitleUseCase.invoke(searchQuery).onSuccess { data ->
                setState { HomeContract.HomeState.DataLoaded(data) }
            }.onFailure {
                setState { HomeContract.HomeState.Error }
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
