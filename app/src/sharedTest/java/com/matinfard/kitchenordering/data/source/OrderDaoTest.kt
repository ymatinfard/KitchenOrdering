package com.matinfard.kitchenordering.data.source

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.matinfard.kitchenordering.data.local.OrderDataBase
import com.matinfard.kitchenordering.model.OrderItemsEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class OrderDaoTest {

    private lateinit var database: OrderDataBase

    @Before
    fun init_db() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            OrderDataBase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun close_db() {
        database.close()
    }

    @Test
    fun test_insert_orderitem_get_orderitem() = runBlocking {

        //Given
        val orderItemEntityTest = OrderItemsEntity(
            0,
            12345,
            "abcd-dffad-addfe-okid",
            "order item",
            1000,
            "http://placehold.it/32x32"
        )

        database.orderDao().insertOrderItems(listOf(orderItemEntityTest))


        //When
        val loaded: List<OrderItemsEntity> = database.orderDao().getAllOrderItems(12345)

        //Then
        assertThat(loaded, notNullValue())
        assertThat(loaded[0].orderId, `is`(orderItemEntityTest.orderId))
        assertThat(loaded[0].guid, `is`(orderItemEntityTest.guid))
        assertThat(loaded[0].name, `is`(orderItemEntityTest.name))
        assertThat(loaded[0].price, `is`(orderItemEntityTest.price))
    }

    @Test
    fun test_total_price_orderitems() = runBlocking {

        // Given
        val orderItemEntityTest1 = OrderItemsEntity(
            0,
            12345,
            "abcd-dffad-addfe-pppp",
            "order item1",
            4000,
            "http://placehold.it/32x32"
        )
        val orderItemEntityTest2 = OrderItemsEntity(
            0,
            12345,
            "bbbb-dffad-addfe-oooo",
            "order item2",
            3000,
            "http://placehold.it/32x32"
        )

        database.orderDao().insertOrderItems(listOf(orderItemEntityTest1, orderItemEntityTest2))


        //When
        val totalPrice = database.orderDao().getTotalPrice(12345)

        //Then
        assertThat(totalPrice, notNullValue())
        assertThat(totalPrice, `is`(orderItemEntityTest1.price + orderItemEntityTest2.price))
    }

    @Test
    fun test_delete_orderItems() = runBlocking {
        //Given
        val orderItemEntityTest = OrderItemsEntity(
            0,
            12345,
            "abcd-dffad-addfe-okid",
            "order item",
            1000,
            "http://placehold.it/32x32"
        )

        database.orderDao().insertOrderItems(listOf(orderItemEntityTest))

        //When
        database.orderDao().deleteAllOrderItems()

        //Then
        val orderItems = database.orderDao().getAllOrderItems(12345)
        assertThat(orderItems.isEmpty(), `is`(true))
    }

}