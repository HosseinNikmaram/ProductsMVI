package com.nikmaram.products.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nikmaram.products.ui.navigation.Navigation.Args.PRODUCT_ID

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.PRODUCTS
    ) {
        composable(
            route = Navigation.Routes.PRODUCTS
        ) {
           // UsersScreenDestination(navController)
        }

        composable(
            route = Navigation.Routes.PRODUCT_DETAIL,
            arguments = listOf(navArgument(name = PRODUCT_ID) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val productID = requireNotNull(backStackEntry.arguments?.getInt(PRODUCT_ID)) { "Product id is required as an argument" }
//            ReposScreenDestination(
//                userId = userId,
//                navController = navController
//            )
        }
    }
}

object Navigation {

    object Args {
        const val PRODUCT_ID = "product_id"
    }

    object Routes {
        const val PRODUCTS = "products"
        const val PRODUCT_DETAIL = "$PRODUCTS/{$PRODUCT_ID}"
    }

}

fun NavController.navigateToProductDetail(productID: Int) {
    navigate(route = "${Navigation.Routes.PRODUCTS}/$productID")
}