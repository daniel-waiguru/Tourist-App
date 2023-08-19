package com.danielwaiguru.touristnews.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.danielwaiguru.touristnews.designsystem.components.OctoKitPrimaryButton
import com.danielwaiguru.touristnews.designsystem.previews.OctoKitDevicePreviews
import com.danielwaiguru.touristnews.presentation.R

@Composable
fun PagingLoadingView(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    error: String? = null,
    onRetry: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        AnimatedVisibility(visible = isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
        AnimatedVisibility(visible = error != null) {
            Text(
                text = error ?: stringResource(id = R.string.network_error_message)
            )
            OctoKitPrimaryButton(
                text = stringResource(id = R.string.retry),
                onClick = onRetry
            )
        }
    }
}

@OctoKitDevicePreviews
@Composable
fun PagingLoadingViewPreview() {
    PagingLoadingView(
        modifier = Modifier.fillMaxWidth(),
        onRetry = {},
        isLoading = true
    )

}