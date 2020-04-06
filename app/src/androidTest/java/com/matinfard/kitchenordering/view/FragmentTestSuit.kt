package com.matinfard.kitchenordering.view

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    LoginFragmentTest::class,
    DashboardFragmentTest::class,
    OrderFragmentTest::class,
    OrderItemsFragmentTest::class,
    ViewListFragmentTest::class,
    SettingsFragmentTest::class
)
class FragmentTestSuit
