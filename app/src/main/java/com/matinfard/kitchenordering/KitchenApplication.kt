package com.matinfard.kitchenordering

import android.app.Application
import com.matinfard.kitchenordering.data.Repository
import com.matinfard.kitchenordering.di.applicationModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class KitchenApplication: Application() {



//    val kitchenRepository: Repository
//        get() = ServiceLocator.provideKitchenRepository(this)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KitchenApplication)
            modules(applicationModule)
        }

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}