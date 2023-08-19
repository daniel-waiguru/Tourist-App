package com.danielwaiguru.touristnews.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.danielwaiguru.touristnews.R
import com.danielwaiguru.touristnews.designsystem.components.OctoKitCenteredTopAppBar
import com.danielwaiguru.touristnews.designsystem.theme.TouristNewsTheme
import com.danielwaiguru.touristnews.presentation.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TouristNewsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            OctoKitCenteredTopAppBar(
                                modifier = Modifier.fillMaxWidth(),
                                title = stringResource(id = R.string.app_name)
                            )
                        }
                    ) { paddingValues ->
                        HomeScreen(
                            modifier = Modifier
                                .padding(paddingValues)
                        )
                    }
                }
            }
        }
    }
}
