package com.danielwaiguru.touristnews.presentation.tourists

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.danielwaiguru.touristnews.presentation.R
import com.danielwaiguru.touristnews.testing.test_data.testTourist
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TouristItemTest {
    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var notApplicable: String

    @Before
    fun setup() {
        rule.activity.apply {
            notApplicable = getString(R.string.n_a)
        }
    }

    @Test
    fun when_touristName_is_null_name_text_id_not_empty() {
        rule.setContent {
            TouristItem(
                tourist = testTourist().copy(touristName = null)
            )
        }
        rule.onNodeWithTag("touristName").assertTextEquals(notApplicable)
    }

    @Test
    fun touristItem_display_given_tourist_data_correctly() {
        val tourist = testTourist()
        rule.setContent {
            TouristItem(tourist = tourist)
        }
        rule.onNodeWithTag("touristName").assertTextEquals(tourist.touristName!!)
        rule.onNodeWithTag("touristLocation").assertTextEquals(tourist.touristLocation)
        rule.onNodeWithTag("tourist_avatar").assertExists()
    }
}