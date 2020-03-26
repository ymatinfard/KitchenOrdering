package com.matinfard.kitchenordering.di

import androidx.room.Room
import com.matinfard.kitchenordering.data.KitchenRepository
import com.matinfard.kitchenordering.data.RemoteDataSource
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.data.local.KitchenLocalDataSource
import com.matinfard.kitchenordering.data.local.OrderDao
import com.matinfard.kitchenordering.data.local.OrderDataBase
import com.matinfard.kitchenordering.data.remote.KitchenRemoteService
import com.matinfard.kitchenordering.utils.Constants
import com.matinfard.kitchenordering.utils.SharedPrefToken
import com.matinfard.kitchenordering.viewmodel.LoginViewModel
import com.matinfard.kitchenordering.viewmodel.SettingsViewModel
import com.matinfard.kitchenordering.viewmodel.SplashScreenViewModel
import com.matinfard.kitchenordering.viewmodel.ViewListSharedViewModel
import okhttp3.OkHttpClient
import org.koin.android.experimental.dsl.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val applicationModule = module (override = true) {

    single { KitchenRemoteService() }

    single { KitchenLocalDataSource(get()) }

    single { KitchenRepository(get(), get()) }

    single  { SharedPrefToken(androidContext()) }

    viewModel{ SplashScreenViewModel()}

    viewModel { LoginViewModel(get())}

    viewModel { SettingsViewModel(get()) }

    viewModel { ViewListSharedViewModel(get())}

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