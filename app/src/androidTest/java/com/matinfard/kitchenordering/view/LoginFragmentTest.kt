package com.matinfard.kitchenordering.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.view.login.LoginFragment
import com.matinfard.kitchenordering.utils.Constants
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*


@RunWith(AndroidJUnit4::class)
@MediumTest
class LoginFragmentTest {


//    @Test
//    fun useAppContext() {
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.matinfard.kitchenordering", appContext.packageName)
//    }

    @Test
    fun test_login_failure(){
        launchFragmentInContainer<LoginFragment>(themeResId = R.style.Theme_AppCompat)
        onView(withId(R.id.et_username)).perform(replaceText("invalid username"))
        onView(withId(R.id.et_password)).perform(replaceText("invalid password"))
        onView(withId(R.id.btn_login)).perform(click())
        onView(withText(Constants.LOGIN_ERROR_MESSAGE)).check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun test_login_success(){
        val validUsername = "user@kitchen.com"
        val validPassword = "password"

        val mockNavController = mock(NavController::class.java)
        launchFragmentInContainer(themeResId = R.style.Theme_AppCompat) {
            LoginFragment()
                .also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(fragment.requireView(), mockNavController)
                    }
                }
            }
        }
        onView(withId(R.id.et_username)).perform(replaceText(validUsername))
        onView(withId(R.id.et_password)).perform(replaceText(validPassword))
        onView(withId(R.id.btn_login)).perform(click())
        verify(mockNavController).navigate(R.id.action_viewListFragment_to_dashboardFragment)
    }

    @Test
    fun test_login_button_is_visible(){
        launchFragmentInContainer<LoginFragment>()
        onView(withId(R.id.btn_login)).check(ViewAssertions.matches(isDisplayed()))
    }
}
