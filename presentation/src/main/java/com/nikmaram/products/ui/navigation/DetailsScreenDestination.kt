package com.nikmaram.products.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nikmaram.products.ui.feature.details.DetailsContract
import com.nikmaram.products.ui.feature.details.DetailsViewModel
import com.nikmaram.products.ui.feature.details.comosables.DetailsScreen

@Composable
fun DetailsScreenDestination(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    DetailsScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        viewModel = viewModel,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { navigationEffect ->
            if (navigationEffect is DetailsContract.Effect.Navigation.Back) {
                navController.popBackStack()
            }
        },
    )
}
