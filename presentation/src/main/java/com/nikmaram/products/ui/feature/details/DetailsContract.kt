package com.nikmaram.products.ui.feature.details

import com.nikmaram.data.model.Product
import com.nikmaram.products.ui.base.ViewEvent
import com.nikmaram.products.ui.base.ViewSideEffect
import com.nikmaram.products.ui.base.ViewState


class DetailsContract {
    sealed class Event : ViewEvent {
        data object Retry : Event()
        data object BackButtonClicked : Event()
    }

    sealed class DetailsState : ViewState {
        data class DataLoaded(val product: Product?) : DetailsState()
        data object LoadingState : DetailsState()
        data object Error : DetailsState()
        data object Initial : DetailsState()    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data object Back : Navigation()
        }
    }
}
