package com.matinfard.kitchenordering.di

import androidx.room.Room
import com.matinfard.kitchenordering.utils.KitchenDataAdapter
import com.matinfard.kitchenordering.data.KitchenRepository
import com.matinfard.kitchenordering.data.RemoteDataSource
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.data.local.KitchenLocalDataSource
import com.matinfard.kitchenordering.data.local.LocalDataSource
import com.matinfard.kitchenordering.data.local.OrderDataBase
import com.matinfard.kitchenordering.data.remote.KitchenRemoteService
import com.matinfard.kitchenordering.view.login.GetUserToken
import com.matinfard.kitchenordering.platform.NetworkHandler
import com.matinfard.kitchenordering.utils.Constants
import com.matinfard.kitchenordering.utils.SharedPrefToken
import com.matinfard.kitchenordering.view.login.LoginViewModel
import com.matinfard.kitchenordering.view.orderitems.GetOrderItems
import com.matinfard.kitchenordering.view.orderitems.OrderItemsViewModel
import com.matinfard.kitchenordering.view.orderitems.SaveOrderItems
import com.matinfard.kitchenordering.view.orderlist.GetAllOrders
import com.matinfard.kitchenordering.view.productlist.GetProducts
import com.matinfard.kitchenordering.view.productlist.SaveOrder
import com.matinfard.kitchenordering.view.productlist.ProductListViewModel
import com.matinfard.kitchenordering.view.settings.RemoveDatabaseInfo
import com.matinfard.kitchenordering.viewmodel.*
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module (override = true) {

    factory { KitchenRemoteService() as RemoteDataSource }

    factory { KitchenLocalDataSource(get()) as LocalDataSource }

    factory { KitchenRepository(get(), get(), get()) as Repository }

    factory { SharedPrefToken(androidContext()) }

    factory { NetworkHandler(androidContext()) }

    factory { RemoveDatabaseInfo(get()) }

    factory { GetProducts(get()) }

    factory { SaveOrder(get()) }

    factory { SaveOrderItems(get()) }

    factory { GetAllOrders(get()) }

    factory { GetOrderItems(get()) }

    factory { GetUserToken(get()) }

    factory { KitchenDataAdapter() }

    viewModel { SettingsViewModel(get(), get()) }

    viewModel{ SplashScreenViewModel()}

    viewModel {
        LoginViewModel(
            get(),
            get()
        )
    }

    viewModel { SettingsViewModel(get(), get()) }

    viewModel { OrderViewModel(get()) }

    viewModel {
        OrderItemsViewModel(
            get()
        )
    }

    viewModel {
        ProductListViewModel(
            get(),
            get(),
            get()
        )
    }

    single {
      Room.databaseBuilder(
        androidContext().applicationContext,
        OrderDataBase::class.java,
        "order_database"
      ).build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}