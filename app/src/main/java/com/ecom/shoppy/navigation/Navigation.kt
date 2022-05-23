package com.ecom.shoppy.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ecom.shoppy.MainActivityViewModel
import com.ecom.shoppy.model.Product
import com.ecom.shoppy.ui.views.*

/**
 * https://stackoverflow.com/questions/65610003/pass-parcelable-argument-with-compose-navigation
 */
@Composable
fun Navigation(mainActivityViewModel: MainActivityViewModel?) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.HomeScreen.route) {
            Dashboard(navController,mainActivityViewModel)
        }
        composable(Screen.ProductDetailsScreen.route,
        arguments = listOf(navArgument("product"){
            type = AssetParamType()
        })) {
            val product = it.arguments?.getParcelable<Product>("product")
            ProductDetailsScreen(navController,product = product, onItemAddedToCart = {quantity: Int, variantIndex: Int, productData:Product ->
                mainActivityViewModel?.addProductToCart(product?.id ?: -1, quantity = quantity,
                    variantIndex = variantIndex,product = productData)
            })
        }
        composable(Screen.AddToCartScreen.route) {
            CartScreenUI(navController, orderDoneMap = mainActivityViewModel?.hashMapOrderDone
                , onPlaceOrder = { orderDoneList ->
                mainActivityViewModel?.placeOrder(orderDoneList)
            })
        }

    }
}