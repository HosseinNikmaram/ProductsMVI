package com.nikmaram.products.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import com.nikmaram.products.ui.home.HomeContract
import com.nikmaram.products.ui.home.HomeViewModel
import com.nikmaram.products.ui.home.comosables.HomeScreen


@Composable
fun ProductsScreenDestination(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        viewModel = viewModel,
        onEventSent = { event ->  viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is HomeContract.Effect.Navigation.ToDetails) {
                navController.navigateToProductDetail(navigationEffect.product.id)
            }
        }
    )
}
