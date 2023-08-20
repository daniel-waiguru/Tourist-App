package com.danielwaiguru.touristnews.presentation.tourists.details

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.danielwaiguru.touristnews.presentation.R
import com.danielwaiguru.touristnews.presentation.tourists.TouristUIState
import com.danielwaiguru.touristnews.testing.dummy_data.notFoundErrorMessage
import com.danielwaiguru.touristnews.testing.test_data.testTourist
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TouristDetailDialogTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var notApplicable: String
    private lateinit var touristInfoTitle: String

    @Before
    fun setup() {
        rule.activity.apply {
            notApplicable = getString(R.string.n_a)
            touristInfoTitle = getString(R.string.tourist_info)
        }
    }

    @Test
    fun dialog_always_has_title() {
        rule.setContent {
            TouristDetailDialog(
                onDismiss = {},
                state = TouristUIState()
            )
        }
        rule.onNodeWithText(touristInfoTitle).assertExists()
    }

    @Test
    fun dialog_has_dismiss_button_with_click_action() {
        rule.setContent {
            TouristDetailDialog(
                onDismiss = {},
                state = TouristUIState()
            )
        }
        rule.onNodeWithTag("dismiss_tourist_dialog").assertHasClickAction()
    }

    @Test
    fun error_state_is_handled_by_displaying_error_view() {
        rule.setContent {
            TouristDetailDialog(
                onDismiss = {},
                state = TouristUIState(errorMessage = notFoundErrorMessage)
            )
        }
        rule.onNodeWithTag("error_view")
            .assertIsDisplayed()
    }

    @Test
    fun loading_state_is_handled_by_displaying_progress_indicator() {
        rule.setContent {
            TouristDetailDialog(
                onDismiss = {},
                state = TouristUIState(isLoading = true)
            )
        }
        rule.onNodeWithTag("octokit_progress_indicator").assertIsDisplayed()
    }

    @Test
    fun success_state_is_handled_by_displaying_tourist_info() {
        rule.setContent {
            TouristDetailDialog(
                onDismiss = {},
                state = TouristUIState(tourist = testTourist())
            )
        }
        rule.onNodeWithTag("tourist_info_content").assertIsDisplayed()
    }
}