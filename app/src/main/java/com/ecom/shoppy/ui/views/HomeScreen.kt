package com.ecom.shoppy.ui.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ecom.shoppy.MainActivityViewModel
import com.ecom.shoppy.R
import com.ecom.shoppy.model.Category
import com.ecom.shoppy.model.ContactUs
import com.ecom.shoppy.model.ProductsDataList
import com.ecom.shoppy.navigation.Screen
import com.ecom.shoppy.ui.theme.*
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.gson.Gson


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = null, mainActivityViewModel = null)
}


@Composable
fun HomeScreen(navController: NavHostController?, mainActivityViewModel: MainActivityViewModel?) {
    val productList = mainActivityViewModel?.productsDataListLiveData?.observeAsState()
    val storeInfo = mainActivityViewModel?.storeInfoLiveData?.observeAsState()
    val categories = storeInfo?.value?.categories
    val progressBar = mainActivityViewModel?.progressBarLiveData?.observeAsState()

    Box(modifier = Modifier
        .fillMaxSize()) {
        Column(modifier = Modifier
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
        ) {
            TopAppBarHeader(storeInfo?.value?.title,storeInfo?.value?.contactUs)
            Spacer(modifier = Modifier.padding(20.dp))
            ProductCategory(categories)
            Spacer(modifier = Modifier.padding(20.dp))
            ProductWidget(navController,productList?.value)
        }
        if(progressBar?.value == true) {
            CircularProgressIndicator(
                Modifier
                    .size(80.dp)
                    .align(Alignment.Center),
                color = orange,
                strokeWidth = 4.dp,
            )
        }
    }


}

@Composable
fun TopAppBarHeader(title: String?, contactUs: ContactUs?) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {

        Text(
            buildAnnotatedString {
                withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                    withStyle(
                        style = SpanStyle(
                            color = subTitleTextColor,
                            fontSize = 24.sp)
                    ) {
                        append("Our\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = titleTextColor,
                            fontSize = 24.sp
                        )
                    ) {
                        append("Products")
                    }

                }
            }
        )

        Card(
            modifier = Modifier.width(100.dp).height(25.dp),
            elevation = 5.dp,
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = title.toString(), textAlign = TextAlign.Center, color = titleTextColor, fontWeight = FontWeight.ExtraBold)
        }

    }
}

@Composable
fun ProductCategory(categories: ArrayList<Category>?) {
    LazyRow(modifier = Modifier
        .fillMaxWidth()) {
        categories?.let {
            itemsIndexed(items = categories) { index,item ->
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .border(
                            color = if (index == 0) orange else lightGrey,
                            width = 2.dp,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 2.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                        Image(painter = painterResource(R.drawable.ic_place_holder), contentDescription = "",
                            modifier = Modifier.size(30.dp, 30.dp))
                        Text(
                            modifier = Modifier
                                .padding(
                                    start = 5.dp,
                                    end = 16.dp,
                                    top = 8.dp,
                                    bottom = 8.dp
                                ),
                            text = item.name ?: "--",
                            color = if (index == 0) lightblack else Color.LightGray
                        )
                    }

                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }
}

@Composable
fun ProductWidget(navController: NavHostController?, productList: ProductsDataList?) {
    FlowRow(mainAxisSpacing = 8.dp,
    mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
        crossAxisSpacing = 8.dp
    ) {
        productList?.products?.forEach {
            ProductItemUI(onItemClick = {
                navController?.navigate(route = Screen.ProductDetailsScreenBase.route+"/${Gson().toJson(it)}")
            },it)
        }
    }
}
