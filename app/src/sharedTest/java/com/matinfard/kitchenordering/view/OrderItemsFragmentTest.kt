package com.matinfard.kitchenordering.view

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.ServiceLocator
import com.matinfard.kitchenordering.data.source.FakeKitchenRepository
import com.matinfard.kitchenordering.utils.Constants
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OrderItemsFragmentTest{

    @Before
    fun init(){
        val repository = FakeKitchenRepository()
        ServiceLocator.kitchenRepository = repository
    }

    @Test
    fun test_recyclerview_item_showing(){
        val orderIdTest = 12345
        val bundle = bundleOf(Constants.ORDER_ID to orderIdTest )
        launchFragmentInContainer<OrderItemsFragment>(bundle)
        onView(withId(R.id.img_order_item)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_order_item_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_order_item_price)).check(matches(isDisplayed()))
    }

}