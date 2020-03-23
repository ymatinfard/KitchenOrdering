package com.matinfard.kitchenordering

import android.app.Application
import com.matinfard.kitchenordering.data.Repository
import com.squareup.picasso.BuildConfig
import timber.log.Timber

class KitchenApplication: Application() {

    val kitchenRepository: Repository
        get() = ServiceLocator.provideKitchenRepository(this)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}