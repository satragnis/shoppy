package com.ecom.shoppy

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.ecom.shoppy.navigation.Navigation
import com.ecom.shoppy.ui.commonComponent.OrderSuccessDialog
import com.ecom.shoppy.ui.theme.ShoppyTheme
import com.ecom.shoppy.utils.FakeApi

class MainActivity : ComponentActivity() {
    lateinit var mainActivityViewModel: MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FakeApi.appContext = this
        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        setContent {
            ShoppyTheme {
                val orderPlaced = mainActivityViewModel.orderPlacedLiveData.observeAsState()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(mainActivityViewModel)
                    if(orderPlaced.value == true){
                        OrderSuccessDialog()
                    }
                }
            }
        }

        mainActivityViewModel.itemAddedToCartLiveData.observeForever{
            if(it == true){
                showToast(getString(R.string.cart_add_success))
            }else if (it == false){
                showToast(getString(R.string.cart_add_fail))
            }
        }

        mainActivityViewModel.orderPlacedLiveData.observeForever{
            if(it == true){
                showToast(getString(R.string.order_placed_success))
            }else if (it == false){
                showToast(getString(R.string.order_place_fail))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        FakeApi.appContext = this
                mainActivityViewModel.getProductList()
                mainActivityViewModel.getStoreInfo()
        }


    private fun showToast(msg:String){
        Toast.makeText(this@MainActivity,msg,Toast.LENGTH_SHORT).show()
    }

    }


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShoppyTheme {
        Navigation(mainActivityViewModel = null)
    }
}

