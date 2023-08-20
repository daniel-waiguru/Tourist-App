package com.danielwaiguru.touristnews.presentation.tourists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danielwaiguru.touristnews.domain.models.Tourist
import com.danielwaiguru.touristnews.presentation.R

@Composable
fun TouristItem(
    tourist: Tourist,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TouristAvatar(
            modifier = Modifier
                .clip(RoundedCornerShape(35))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .size(45.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = tourist.touristName ?: stringResource(id = R.string.n_a),
                modifier = Modifier
                    .testTag("touristName")
            )
            Text(
                text = tourist.touristLocation,
                style = LocalTextStyle.current.copy(
                    fontSize = 12.sp
                ),
                modifier = Modifier
                    .testTag("touristLocation")
            )
        }
    }
}

@Composable
fun TouristAvatar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .testTag("tourist_avatar"),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Rounded.Person,
            contentDescription = null,
            modifier = Modifier.size(25.dp),
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}