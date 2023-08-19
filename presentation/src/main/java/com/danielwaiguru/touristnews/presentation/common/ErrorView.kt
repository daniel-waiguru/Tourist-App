package com.danielwaiguru.touristnews.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.danielwaiguru.touristnews.designsystem.previews.OctoKitDevicePreviews
import com.danielwaiguru.touristnews.presentation.R

@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.error_title),
    description: String?
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall
        )
        Text(text = description ?: stringResource(id = R.string.an_error_occured))
    }
}

@OctoKitDevicePreviews
@Composable
fun EmptyViewPreview() {
    ErrorView(
        title = "No Data FOund",
        description = "Data will appear here",
        modifier = Modifier.fillMaxSize()
    )
}
