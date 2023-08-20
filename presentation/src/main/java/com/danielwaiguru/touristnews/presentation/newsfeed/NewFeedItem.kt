package com.danielwaiguru.touristnews.presentation.newsfeed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danielwaiguru.touristnews.designsystem.components.OctoKitAsyncImage
import com.danielwaiguru.touristnews.designsystem.previews.OctoKitDevicePreviews
import com.danielwaiguru.touristnews.domain.models.Article
import com.danielwaiguru.touristnews.presentation.R

@Composable
fun NewFeedItem(
    modifier: Modifier = Modifier,
    article: Article
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OctoKitAsyncImage(
            url = article.url,
            contentDescription = article.title ?: stringResource(id = R.string.n_a),
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .size(90.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = article.title ?: stringResource(id = R.string.n_a),
                style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                ),
                modifier = Modifier
                    .testTag("article_title")
            )
            Text(
                text = article.description ?: stringResource(id = R.string.n_a),
                maxLines = 3,
                softWrap = true,
                style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 11.sp
                ),
                modifier = Modifier
                    .testTag("article_desc")
            )
            ArticleAuthorSection(
                user = article.user,
                createdAt = article.createdAt ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .testTag("article_author")
            )
        }
    }
}

@OctoKitDevicePreviews
@Composable
fun NewFeedItemPreview() {
    NewFeedItem(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .padding(8.dp),
        article = dummyArticle
    )
}

@Composable
private fun ArticleAuthorSection(
    modifier: Modifier = Modifier,
    user: Article.User,
    createdAt: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OctoKitAsyncImage(
            url = user.profilePicture ?: "",
            contentDescription = stringResource(id = R.string.article_author_image_content_desc),
            modifier = Modifier
                .clip(CircleShape)
                .size(35.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = user.name,
                style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
            )
            Text(
                text = createdAt,
                style = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.Normal,
                    fontSize = 10.sp
                )
            )
        }
    }
}

@OctoKitDevicePreviews
@Composable
fun ArticleAuthorSectionPreview() {
    ArticleAuthorSection(
        user = dummyUser,
        createdAt = "19 August 2023",
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

val dummyUser = Article.User(
    name = "Daniel",
    "https://www.adequatetravel.com/ATMultimedia/UserProfileCover/7059/2bc2d9ae-4bc0-445a-852f-e3b49d05c553.png"
)

val dummyArticle = Article(
    createdAt = "19 August 2023",
    description = "Sample description",
    title = "Sample Title",
    id = 1,
    location = "Nairobi",
    url = "",
    user = dummyUser
)