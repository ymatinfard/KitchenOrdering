package com.matinfard.kitchenordering

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.matinfard.kitchenordering.data.KitchenRepository
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.data.local.KitchenLocalDataSource
import com.matinfard.kitchenordering.data.local.LocalDataSource
import com.matinfard.kitchenordering.data.local.OrderDataBase
import com.matinfard.kitchenordering.data.remote.KitchenRemoteService

object ServiceLocator {

    private var database: OrderDataBase? = null
    @Volatile
    var kitchenRepository: Repository? = null
        @VisibleForTesting set

    fun provideKitchenRepository(context: Context): Repository {
        synchronized(this) {
            return kitchenRepository ?: createKitchenRepository(context)
        }
    }

    private fun createKitchenRepository(context: Context): Repository {
        val newRepo = KitchenRepository(KitchenRemoteService(), createKitchenLocalDataSource(context))
        kitchenRepository = newRepo
        return newRepo
    }

    private fun createKitchenLocalDataSource(context: Context): LocalDataSource {
        val database = database ?: createDataBase(context)
        return KitchenLocalDataSource(database.orderDao())
    }

    private fun createDataBase(context: Context): OrderDataBase {
        val result = Room.databaseBuilder(
            context.applicationContext,
            OrderDataBase::class.java,
            "order_database"
        ).build()
        database = result
        return result
    }

}