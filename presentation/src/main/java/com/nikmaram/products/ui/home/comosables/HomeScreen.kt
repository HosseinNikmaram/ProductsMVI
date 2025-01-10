package com.nikmaram.products.ui.home.comosables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nikmaram.products.ui.base.SIDE_EFFECTS_KEY
import com.nikmaram.products.ui.composable.common.NetworkError
import com.nikmaram.products.ui.composable.common.Progress
import com.nikmaram.products.ui.home.HomeContract
import com.nikmaram.products.ui.utility.generateFakeProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun HomeScreen(
    state: HomeContract.HomeState,
    effectFlow: Flow<HomeContract.Effect>?,
    onEventSent: (event: HomeContract.Event) -> Unit,
    onNavigationRequested: (navigationEffect: HomeContract.Effect.Navigation) -> Unit
) {

    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is HomeContract.Effect.Navigation.ToDetails -> onNavigationRequested(effect)
            }
        }?.collect()
    }

    Scaffold(
        topBar = { HomeTopBar() }
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            when (state) {
                is HomeContract.HomeState.DataLoaded -> {
                    HomeListItems(
                        products = state.products,
                        onProductClicked = {
                            onEventSent(HomeContract.Event.OnProductClicked(it))
                        }
                    )
                }

                is HomeContract.HomeState.Error -> {
                    NetworkError { onEventSent(HomeContract.Event.Retry) }
                }

                is HomeContract.HomeState.Initial -> {
                    Progress(Modifier.fillMaxSize())
                }

                is HomeContract.HomeState.LoadingState -> {
                    Progress(Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersScreenSuccessPreview() {
    HomeScreen(
        state = HomeContract.HomeState.DataLoaded(
            products = generateFakeProduct(),
        ),
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}

@Preview(showBackground = true)
@Composable
fun HomeScreenErrorPreview() {
    HomeScreen(
        state = HomeContract.HomeState.Error,
        effectFlow = null,
        onEventSent = {},
        onNavigationRequested = {},
    )
}