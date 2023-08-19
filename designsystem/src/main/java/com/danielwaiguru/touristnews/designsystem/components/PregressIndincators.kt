package com.danielwaiguru.touristnews.designsystem.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun OctoKitProgressIndicator(
    modifier: Modifier = Modifier,
    stockWidth: Dp = 2.dp,
    color: Color = Color.White
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = color,
        strokeWidth = stockWidth
    )
}