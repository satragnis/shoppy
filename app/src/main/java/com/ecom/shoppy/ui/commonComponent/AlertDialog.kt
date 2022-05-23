package com.ecom.shoppy.ui.commonComponent

import android.widget.Toast
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.ecom.shoppy.R
import com.ecom.shoppy.ui.theme.black
import com.ecom.shoppy.ui.theme.grey
import com.ecom.shoppy.ui.theme.orange
import com.ecom.shoppy.ui.theme.titleTextColor


@Composable
fun OrderSuccessDialog() {
    // below line is use to get
    // the context for our app.
    val context = LocalContext.current

    // below line is use to set our state
    // of dialog box to open as true.
    val openDialog = remember { mutableStateOf(true) }
    // below line is to check if the
    // dialog box is open or not.
    if (openDialog.value) {
        // below line is use to
        // display a alert dialog.
        AlertDialog(
            // on dialog dismiss we are setting
            // our dialog value to false.
            onDismissRequest = { openDialog.value = false },

            // below line is use to display title of our dialog
            // box and we are setting text color to white.
            title = { Text(text = "Shoppy", color = titleTextColor) },

            // below line is use to display
            // description to our alert dialog.
            text = { Text("Order has been placed successfully!", color = black) },

            // in below line we are displaying
            // our confirm button.
            confirmButton = {
                // below line we are adding on click
                // listener for our confirm button.
                TextButton(
                    onClick = {
                        openDialog.value = false
                        Toast.makeText(context, "Confirm Button Click", Toast.LENGTH_LONG).show()
                    }
                ) {
                    // in this line we are adding
                    // text for our confirm button.
                    Text("Confirm", color = orange)
                }
            },
            // in below line we are displaying
            // our dismiss button.
            dismissButton = {
                // in below line we are displaying
                // our text button
                TextButton(
                    // adding on click listener for this button
                    onClick = {
                        openDialog.value = false
                        Toast.makeText(context, "Dismiss Button Click", Toast.LENGTH_LONG).show()
                    }
                ) {
                    // adding text to our button.
                    Text("Dismiss", color = grey)
                }
            },
            // below line is use to add background color to our alert dialog
            backgroundColor = colorResource(id = R.color.white),

            // below line is use to add content color for our alert dialog.
            contentColor = Color.White
        )
    }
}

