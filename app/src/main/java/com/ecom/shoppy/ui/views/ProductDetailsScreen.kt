package com.ecom.shoppy.ui.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.ecom.shoppy.ui.commonComponent.TopAppBarWithBack
import com.ecom.shoppy.ui.theme.*
import com.ecom.shoppy.R
import com.ecom.shoppy.model.Product
import com.ecom.shoppy.model.Variant
import com.ecom.shoppy.navigation.Screen
import com.ecom.shoppy.utils.getColor

var quantity: Int = 0
var variantIndex: Int = 0

@Preview(showBackground = true)
@Composable
fun ProductDetailsScreenPreview() {
    ProductDetailsScreen(navController = null, product = null, onItemAddedToCart = { _, _ , _ -> })
}

@Composable
fun ProductDetailsScreen(
    navController: NavHostController? = null,
    product: Product?,
    onItemAddedToCart: (quantity: Int, variantIndex: Int,product:Product) -> Unit
) {
    product?.let { productData->
        Scaffold(
            topBar = {
                TopAppBarWithBack(
                    onBackClick = {
                        navController?.navigate(Screen.HomeScreen.route)
                    },
                )
            }, backgroundColor = lightgraybg,
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                            onItemAddedToCart(quantity, variantIndex,productData)
                    },
                    backgroundColor = orange,
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add To Cart",
                        tint = white
                    )
                }
            },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(it)
                ) {
                    ConstraintLayout {
                        val (imagesliderref, addtocartref) = createRefs()
                        Box(modifier = Modifier
                            .height(280.dp)
                            .constrainAs(imagesliderref) {
                                top.linkTo(imagesliderref.top)
                                bottom.linkTo(imagesliderref.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }) {
                            ProductImage()
                        }
                        Surface(
                            color = white,
                            shape = RoundedCornerShape(40.dp)
                                .copy(
                                    bottomStart = ZeroCornerSize,
                                    bottomEnd = ZeroCornerSize
                                ),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 240.dp)
                                .constrainAs(addtocartref) {
                                    bottom.linkTo(parent.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(30.dp)
                            ) {
                                ProductTitle(productData.name, productData.price)
                                Spacer(modifier = Modifier.padding(10.dp))
                                ProductAvailableSize(productData.variants)
                                Spacer(modifier = Modifier.padding(10.dp))
                                Counter()
                                Spacer(modifier = Modifier.padding(10.dp))
                                ProductItemColorWithDesc(product)
                            }
                        }
                    }
                }
            }
        )
    }
}


@Composable
fun ProductImage() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Image(
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.ic_place_holder),
            contentDescription = "",
            modifier = Modifier
                .size(230.dp)
        )
    }
}

@Composable
fun ProductTitle(name: String?, price: Int?) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name ?: "-----------",
                color = titleTextColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Column(modifier = Modifier.wrapContentHeight()) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                orange,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("₹ ")
                        }
                        withStyle(
                            style = SpanStyle(
                                titleTextColor
                            )
                        ) {
                            append((price ?: 0).toString())
                        }
                    },
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier,
                    fontSize = 16.sp

                )


            }
        }
    }
}

@Composable
fun ProductAvailableSize(variants: List<Variant>?) {
    variants?.let {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.available_sizes),
                color = titleTextColor,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.padding(10.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                itemsIndexed(items = it) { index, item ->
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .width(70.dp)
                            .border(
                                color = if (index==0) orange else lightGrey,
                                width = 2.dp,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .clickable {
                            }) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 20.dp,
                                    end = 16.dp,
                                    top = 10.dp,
                                    bottom = 8.dp
                                ),
                            text = item.size.toString(),
                            fontWeight = FontWeight.Bold,
                            color = if (index==0) titleTextColor else Color.LightGray
                        )


                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }

            }
        }
    }
}

@Composable
fun ProductItemColorWithDesc(product: Product?) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.color_label),
            color = titleTextColor,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.padding(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            product?.variants?.forEach {
                ColorRound(color = it.color)
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Text(
            text = stringResource(R.string.description_label),
            color = titleTextColor,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Text(
            text = stringResource(id = R.string.product_text_description),
            color = lightblack,
            fontSize = 14.sp
        )
    }

}


@Composable
fun ColorRound(color: String?) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .clip(shape = CircleShape)
            .background(color?.getColor() ?: Color.Unspecified)
            .clickable { }
    )
}

@Composable
fun Counter() {
    val counter: MutableState<Int> = remember {
        mutableStateOf(0)
    }
    quantity = counter.value
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        Text(
            text = stringResource(R.string.quantity_label),
            color = titleTextColor,
            fontSize = 18.sp,
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(horizontalArrangement = Arrangement.End) {
            IconButton(onClick = { if (counter.value > 0) counter.value-- })
            {
                Icon(
                    Icons.Rounded.Clear,
                    "reduce Quantity",
                    tint = orange
                )
            }
            Text(
                text = "${counter.value}", modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )
            IconButton(onClick = { if (counter.value <= 10) counter.value++ }) {
                Icon(
                    Icons.Rounded.Add,
                    "Add Quantity",
                    tint = orange
                )
            }
        }

    }
}






