package com.dicoding.sayurtaniku

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.dicoding.sayurtaniku.model.FakeSayurDataSource
import com.dicoding.sayurtaniku.ui.navigation.Screen
import com.dicoding.sayurtaniku.ui.theme.SayurTanikuTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SayurTanikuAppKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navCotroller: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SayurTanikuTheme {
                navCotroller = TestNavHostController(LocalContext.current)
                navCotroller.navigatorProvider.addNavigator(ComposeNavigator())
                SayurTanikuApp(navController = navCotroller)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navCotroller.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("SayurList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeSayurDataSource.dummySayurku[10].title).performClick()
        navCotroller.assertCurrentRouteName(Screen.DetailSayur.route)
        composeTestRule.onNodeWithText(FakeSayurDataSource.dummySayurku[10].title).assertIsDisplayed()
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_cart).performClick()
        navCotroller.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navCotroller.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navCotroller.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("SayurList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeSayurDataSource.dummySayurku[10].title).performClick()
        navCotroller.assertCurrentRouteName(Screen.DetailSayur.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navCotroller.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_checkout_rightBackStack() {
        composeTestRule.onNodeWithText(FakeSayurDataSource.dummySayurku[4].title).performClick()
        navCotroller.assertCurrentRouteName(Screen.DetailSayur.route)
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").performClick()
        navCotroller.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navCotroller.assertCurrentRouteName(Screen.Home.route)
    }
}