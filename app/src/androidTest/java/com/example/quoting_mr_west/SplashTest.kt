package com.example.quoting_mr_west


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.quoting_mr_west.view.SplashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SplashTest {

    @get:Rule
    val activityRule: ActivityScenarioRule<SplashActivity> =
        ActivityScenarioRule(SplashActivity::class.java)

    @Test
    fun testActivityStart() {
        onView(withId(R.id.splash_layout))
            .check(matches(isCompletelyDisplayed()))
    }

}

