package com.danielwaiguru.touristnews.presentation.tourists.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.touristnews.designsystem.components.OctoKitProgressIndicator
import com.danielwaiguru.touristnews.designsystem.previews.OctoKitDevicePreviews
import com.danielwaiguru.touristnews.domain.models.Tourist
import com.danielwaiguru.touristnews.presentation.R
import com.danielwaiguru.touristnews.presentation.common.ErrorView
import com.danielwaiguru.touristnews.presentation.tourists.TouristAvatar
import com.danielwaiguru.touristnews.presentation.tourists.TouristUIState
import com.danielwaiguru.touristnews.presentation.tourists.TouristsViewModel

@Composable
fun TouristDetailDialog(
    touristId: Int,
    onDismiss: () -> Unit,
    viewModel: TouristsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = touristId) {
        viewModel.getTouristById(touristId)
    }
    val uiState by viewModel.touristUIState.collectAsStateWithLifecycle()
    TouristDetailDialog(
        onDismiss = onDismiss,
        state = uiState
    )
}

@Composable
fun TouristDetailDialog(
    onDismiss: () -> Unit,
    state: TouristUIState
) {
    val configuration = LocalConfiguration.current
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .widthIn(max = configuration.screenWidthDp.dp - 70.dp)
                .clip(MaterialTheme.shapes.large)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.tourist_info),
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge
                )
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .testTag("dismiss_tourist_dialog")
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = stringResource(id = R.string.dismiss_dialog)
                    )
                }
            }
            if (state.isLoading) {
                OctoKitProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            } else if (state.errorMessage.isNullOrBlank().not()) {
                ErrorView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    description = state.errorMessage
                )
            } else {
                TouristInfoContent(
                    tourist = state.tourist,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .testTag("tourist_info_content")
                )
            }
        }
    }
}

@Composable
fun TouristInfoContent(
    tourist: Tourist?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        TouristAvatar(
            modifier = Modifier
                .clip(RoundedCornerShape(35))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .size(50.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        InfoItem(
            title = stringResource(id = R.string.name),
            detail = tourist?.touristName
        )
        Spacer(modifier = Modifier.height(8.dp))
        InfoItem(
            title = stringResource(id = R.string.email),
            detail = tourist?.touristEmail
        )
        Spacer(modifier = Modifier.height(8.dp))
        InfoItem(
            title = stringResource(id = R.string.location),
            detail = tourist?.touristLocation
        )
    }
}

@Composable
private fun InfoItem(
    title: String,
    detail: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = LocalTextStyle.current.copy(
                fontSize = 12.sp
            )
        )
        Text(
            text = detail ?: stringResource(id = R.string.n_a)
        )
    }
}

@OctoKitDevicePreviews
@Composable
fun InfoItemPreview() {
    InfoItem(
        title = "Name",
        detail = "Daniel",
        modifier = Modifier.fillMaxWidth()
    )
}