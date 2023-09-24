package com.example.desafiosicredi.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.desafiosicredi.R

@Composable
fun ImagePlaceholder() {
    Image(
        painter = painterResource(id = R.drawable.image_placeholder),
        contentDescription = null
    )
}