package com.ecom.shoppy.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecom.shoppy.R
import com.ecom.shoppy.model.Product
import com.ecom.shoppy.model.Variant
import com.ecom.shoppy.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductItemUI(onItemClick: () -> Unit, product: Product) {
    Card(modifier = Modifier.size(160.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = 2.dp,
        onClick = onItemClick
    ) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .clip(CircleShape)
                    .background(lightorange),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(80.dp),
                    painter = painterResource(R.drawable.ic_place_holder),
                    contentDescription = "",
                )
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {

                Text(text = product.name ?: "Product",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = titleTextColor
                )

                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                orange,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("₹")
                        }
                        withStyle(
                            style = SpanStyle(
                                titleTextColor
                            )
                        ) {
                            append(product.price.toString())
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


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   ProductItemUI(onItemClick = {}, product = Product(
       id = 1,
       categoryId = 1,
       name = "Product",
       dateAdded = "44/22/20",
       variants = arrayListOf(),
   )
   )
}