package com.example.desafiosicredi.util.extensions

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

fun Modifier.shimmerEffect(enabled: Boolean, cornerRadius: Dp = 100.dp): Modifier {
    return composed {
        placeholder(
            visible = enabled,
            highlight = PlaceholderHighlight.shimmer(),
            shape = RoundedCornerShape(cornerRadius)
        )
    }
}