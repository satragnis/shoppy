package com.ecom.shoppy.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ecom.shoppy.ui.theme.titleTextColor


@Preview
@Composable
fun CartEmptyPreview(){
    CartEmpty()
}

@Composable
fun CartEmpty(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Your Cart is Empty", textAlign = TextAlign.Center, color = titleTextColor, fontSize = 28.sp)
    }
}