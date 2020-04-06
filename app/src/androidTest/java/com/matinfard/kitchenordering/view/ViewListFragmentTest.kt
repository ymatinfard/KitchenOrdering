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
import com.matinfard.kitchenordering.data.Repository

import com.matinfard.kitchenordering.view.productlist.ProductListFragment
import com.matinfard.kitchenordering.utils.SharedPrefToken
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.koin.test.inject


@RunWith(AndroidJUnit4::class)
@MediumTest
class ViewListFragmentTest: AutoCloseKoinTest() {

    private val shearedPref by inject<SharedPrefToken>()

    @Before
    fun init(){

        loadKoinModules(
            module(override = true){ factory{ FakeRepository() as Repository}}
        )

        shearedPref.userToken = "5e8c3c48-af49-425b-a6d9-f37f3511e4fa"
        launchFragmentInContainer<ProductListFragment>()
    }

    @Test
    fun test_product_recyclerview_item_showing(){
        onView(withId(R.id.tv_product_name)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_add_to_cart)).check(matches((isDisplayed())))
        onView(withId(R.id.tv_product_price)).check(matches((isDisplayed())))
        onView(withId(R.id.btn_order)).check(matches(isDisplayed()))
    }

    @Test
    fun test_product_selected_icon_showing(){
        onView(withId(R.id.btn_add_to_cart)).check(matches((isDisplayed())))
        onView(withId(R.id.btn_add_to_cart)).perform(click())
        onView(withId(R.id.img_product_selected)).check(matches(isDisplayed()))
    }

    @Test
    fun test_making_order(){
        val mockNavController = mock(NavController::class.java)
        observeFragmentNavigation(mockNavController)
        onView(withId(R.id.btn_add_to_cart)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_order)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_add_to_cart)).perform(click())
        onView(withId(R.id.btn_order)).perform(click())
        verify(mockNavController).navigate(R.id.action_viewListFragment_to_orderFragment)

    }

    private fun observeFragmentNavigation(mockNavController: NavController) {
        launchFragmentInContainer {
            ProductListFragment()
                .also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        Navigation.setViewNavController(fragment.requireView(), mockNavController)
                    }
                }
            }
        }
    }
}