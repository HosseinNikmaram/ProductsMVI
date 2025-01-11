package com.nikmaram.products.ui.home

import androidx.compose.runtime.Immutable
import com.nikmaram.data.model.Product
import com.nikmaram.products.ui.base.ViewEvent
import com.nikmaram.products.ui.base.ViewSideEffect
import com.nikmaram.products.ui.base.ViewState

class HomeContract {
    sealed class Event : ViewEvent {
        data object Retry : Event()
        data class OnProductClicked(val product: Product) : Event()
        data class OnSearchQueryChanged(val query: String?) : Event()
    }

    @Immutable
    sealed class HomeState : ViewState {
        data class DataLoaded(val products: List<Product>?) : HomeState()
        data object LoadingState : HomeState()
        data object Error : HomeState()
        data object Initial : HomeState()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToDetails(val product: Product) : Navigation()
        }
    }
}
