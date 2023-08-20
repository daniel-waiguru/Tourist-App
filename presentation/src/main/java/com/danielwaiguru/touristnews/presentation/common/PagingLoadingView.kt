package com.danielwaiguru.touristnews.presentation.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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
            .testTag("paging_loading_view")
    ) {
        AnimatedVisibility(visible = isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
        AnimatedVisibility(visible = error != null) {
            PagingErrorView(
                error = error,
                onRetry = onRetry,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun PagingErrorView(
    error: String?,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = error ?: stringResource(id = R.string.network_error_message),
            textAlign = TextAlign.Center
        )
        OctoKitPrimaryButton(
            text = stringResource(id = R.string.retry),
            onClick = onRetry,
            modifier = Modifier
                .widthIn(min = 110.dp)
        )
    }
}

@OctoKitDevicePreviews
@Composable
fun PagingLoadingViewPreview() {
    PagingLoadingView(
        modifier = Modifier.fillMaxWidth(),
        onRetry = {},
        isLoading = false,
        error = "And error occurred"
    )
}