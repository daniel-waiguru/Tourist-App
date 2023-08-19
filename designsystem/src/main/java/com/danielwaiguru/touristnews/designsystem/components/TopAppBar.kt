package com.danielwaiguru.touristnews.designsystem.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.danielwaiguru.touristnews.designsystem.R
import com.danielwaiguru.touristnews.designsystem.previews.OctoKitDevicePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OctoKitCenteredTopAppBar(
    modifier: Modifier = Modifier,
    onNavIconPressed: (() -> Unit)? = null,
    navigationIcon: ImageVector? = null,
    title: String,
    actions: @Composable RowScope.() -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier,
        actions = actions,
        navigationIcon =  {
            if (navigationIcon != null && onNavIconPressed != null) {
                IconButton(onClick = onNavIconPressed) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = stringResource(id = R.string.nav_icon_content_desc),
                    )
                }
            }
        }
    )
}

@OctoKitDevicePreviews
@Composable
private fun OctoKitCenteredTopAppBarPreview() {
    OctoKitCenteredTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = "Sample Title"
    )
}

@OctoKitDevicePreviews
@Composable
private fun OctoKitCenteredTopAppBarWithActionsPreview() {
    OctoKitCenteredTopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = "Sample Title",
        onNavIconPressed = {},
        navigationIcon = Icons.Outlined.ArrowBack,
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Rounded.Settings, contentDescription = null)
            }
        }
    )
}