package com.example.desafiosicredi.ui.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MessageDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = title, fontSize = 20.sp)
        },
        text = {
            Text(text = message, fontSize = 16.sp)
        },
        buttons = {
            Button(
                onClick = {
                    onDismiss()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(text = "OK", color = Color.White)
            }
        }
    )
}

@Preview
@Composable
fun MessageDialogPreview() {
    MessageDialog(title = "This is a title", message = "This is a message") {
    }
}