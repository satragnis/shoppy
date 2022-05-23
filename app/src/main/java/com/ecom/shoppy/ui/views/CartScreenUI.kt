package com.ecom.shoppy.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ecom.shoppy.R
import com.ecom.shoppy.model.OrderDone
import com.ecom.shoppy.model.OrderDoneList
import com.ecom.shoppy.navigation.Screen
import com.ecom.shoppy.ui.theme.*
import java.util.*
import kotlin.collections.HashMap


@Preview(showBackground = true)
@Composable
fun AddToCartScreenPreview(){
    val hashMapOrderDone = HashMap<Int,OrderDone>()
    hashMapOrderDone[1] =
        OrderDone(
        productId = 1,
        categotyId = 2,
        name = "Sample Name",
        variantId = 1,
        color = "white",
        size = 2,
        price = 2000,
        quantity = 2,
        taxPercent = 12.5,
        isOrderDone = false,
    )
    CartScreenUI(navController = null, orderDoneMap = hashMapOrderDone,{})
}




@Composable
fun CartScreenUI(navController: NavHostController?, orderDoneMap: HashMap<Int,OrderDone>?, onPlaceOrder: (OrderDoneList) -> Unit) {
    orderDoneMap?.let {
        if(it.size>0){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp)
            ) {
                DeleteCart(navController)
                Spacer(modifier = Modifier.padding(20.dp))
                CartItemList(it)
            }
            Box(modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 30.dp)){
                NextButtonWithTotalItems(onPlaceOrder,it)
            }
        }
    } else {
           CartEmpty()
        }
    }
}


@Composable
fun DeleteCart(navController: NavHostController?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                    withStyle(
                        style = SpanStyle(
                            color = subTitleTextColor,
                            fontSize = 24.sp
                        )
                    ) {
                        append("Shopping\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = titleTextColor,
                            fontSize = 24.sp
                        )
                    ) {
                        append("Cart")
                    }

                }
            }
        )

        IconButton(onClick = {
            navController?.navigate(Screen.HomeScreen.route)
        }) {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "",
                tint = orange
            )

        }
    }
}

@Composable
fun CartItemList(orderDoneMap: HashMap<Int,OrderDone>) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
            orderDoneMap.forEach { (_, value) ->
                ProductCartItems(
                    imagePainter = painterResource(id = R.drawable.ic_place_holder),
                    title = value.name.toString(),
                    price = value.price.toString(),
                    pricetag = "₹",
                    count = "x${value.quantity}",
                    backgroundColor = lightsilverbox
                )
            }
    }
}

@Composable
fun NextButtonWithTotalItems(onPlaceOrder: (OrderDoneList) -> Unit, hashMap: HashMap<Int, OrderDone>) {
    var totalAmount:Double = 0.0
    var totalTax:Double = 0.0
    val items = hashMap.size
        hashMap.forEach{ (_,value) ->
            val tax:Double = value.taxPercent ?: 0.0
            val price:Int = value.price ?: 0
            val quantity = value.quantity ?: 1
            totalTax = (totalTax + ((tax*price)/100)*quantity)
            totalAmount += price * quantity
        }
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Bottom) {
        Divider(color = lightGrey, thickness = 2.dp)
        Spacer(modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$items Items",
                fontSize = 14.sp,
                color = lightGrey
            )

            Text(
                text = "₹$totalAmount",
                fontSize = 18.sp,
                color = titleTextColor,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.tax_label),
                fontSize = 14.sp,
                color = lightGrey
            )

            Text(
                text = "₹$totalTax",
                fontSize = 18.sp,
                color = titleTextColor,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(top = 8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Total Amount",
                fontSize = 16.sp,
                color = lightGrey
            )

            Text(
                text = "₹${totalTax+totalAmount}",
                fontSize = 20.sp,
                color = titleTextColor,
                fontWeight = FontWeight.Bold
            )
        }

        Button(
            onClick = { onPlaceOrder(
                OrderDoneList(
                    totalAmount = totalAmount,
                    totalQuantity = items,
                    modeOfPayment = "Online",
                    transactionId = UUID.randomUUID().toString(),
                    orderId = UUID.randomUUID().toString(),
                ))
                      },
            colors = ButtonDefaults.buttonColors(backgroundColor = orange),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 30.dp,
                    bottom = 34.dp
                )
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                text = "Place Order",
                color = white,
                style = MaterialTheme.typography.button,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )
        }

    }
}
