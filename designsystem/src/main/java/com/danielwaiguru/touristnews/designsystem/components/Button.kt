package com.danielwaiguru.touristnews.designsystem.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.danielwaiguru.touristnews.designsystem.previews.OctoKitDevicePreviews

@Composable
fun OctoKitPrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = ButtonDefaults.shape,
    onClick: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(
        top = 8.dp,
        bottom = 8.dp
    )
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = shape,
        contentPadding = contentPadding
    ) {
        Text(
            text = text
        )
    }
}

@OctoKitDevicePreviews
@Composable
private fun OctoKitPrimaryButtonPreview() {
    OctoKitPrimaryButton(
        text = "Click",
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    )
}