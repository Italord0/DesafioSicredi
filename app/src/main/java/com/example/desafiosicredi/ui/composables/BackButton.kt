package com.example.desafiosicredi.ui.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.desafiosicredi.R

@Composable
fun BackButton(
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back_arrow), // Replace with your arrow icon
            contentDescription = stringResource(id = R.string.back),
            tint = Color.Black
        )
    }
}

@Preview
@Composable
private fun BackButtonPreview() {
    BackButton {}
}