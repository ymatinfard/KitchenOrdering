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
import com.matinfard.kitchenordering.R
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class DashboardFragmentTest {

    @Test
    fun test_viewlist_button_navigation() {
        val mockNavController = mock(NavController::class.java)
        observeFragmentNavigation(mockNavController)
        onView(withId(R.id.img_view_list)).check(matches(isDisplayed()))
        onView(withId(R.id.img_view_list)).perform(click())
        verify(mockNavController).navigate(R.id.action_dashboardFragment_to_viewListFragment)
    }

    @Test
    fun test_order_button_navigation(){
        val mockNavController = mock(NavController::class.java)
        observeFragmentNavigation(mockNavController)
        onView(withId(R.id.img_order)).check(matches(isDisplayed()))
        onView(withId(R.id.img_order)).perform(click())
        verify(mockNavController).navigate(R.id.action_dashboardFragment_to_orderFragment)
    }

    @Test
    fun test_settings_button_navigation(){
        val mockNavController = mock(NavController::class.java)
        observeFragmentNavigation(mockNavController)
        onView(withId(R.id.img_settings)).check(matches(isDisplayed()))
        onView(withId(R.id.img_settings)).perform(click())
        verify(mockNavController).navigate(R.id.action_dashboardFragment_to_settingsFragment)
    }

    private fun observeFragmentNavigation(mockNavController: NavController) {
        launchFragmentInContainer {
            DashboardFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(fragment.requireView(), mockNavController)
                    }
                }
            }
        }
    }
}