package com.example.quoting_mr_west

import android.app.Activity
import androidx.databinding.adapters.ViewBindingAdapter
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quoting_mr_west.view.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_fragment_is_displayed() {
        onView(withId(R.id.fragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_refresh_layout_is_displayed() {

        onView(withId(R.id.refresh_layout)).check(matches(isDisplayed()))

        onView(withId(R.id.quote_text_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_share_button(){
        onView(withId(R.id.fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.action_share)).check(matches(isDisplayed()))
        onView(withId(R.id.action_share)).perform(click())
    }

}