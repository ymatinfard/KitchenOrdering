package com.matinfard.kitchenordering.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.matinfard.kitchenordering.R
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
@MediumTest
class SettingsFragmentTest{

    @Test
    fun test_logout_navigation_to_login_fragment(){
        val mockNavController = mock(NavController::class.java)
        observeFragmentNavigation(mockNavController)
        onView(withId(R.id.btn_logout)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_logout)).perform(click())
        verify(mockNavController).navigate(R.id.action_settingsFragment_to_loginFragment)
    }

    private fun observeFragmentNavigation(mockNavController: NavController) {
        launchFragmentInContainer {
            SettingsFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(fragment.requireView(), mockNavController)
                    }
                }
            }
        }
    }
}