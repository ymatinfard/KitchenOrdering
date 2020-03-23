package com.matinfard.kitchenordering.view


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.matinfard.kitchenordering.R
import com.matinfard.kitchenordering.ServiceLocator
import com.matinfard.kitchenordering.data.source.FakeKitchenRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class OrderFragmentTest{

    @Before
    fun init(){
        val repository = FakeKitchenRepository()
        ServiceLocator.kitchenRepository = repository
        launchFragmentInContainer<OrderFragment>()
    }

    @Test
    fun test_recyclerview_item_showing(){
        onView(withId(R.id.img_order_item)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_order_count)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_order_date_time)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_order_id)).check(matches(isDisplayed()))
    }

}