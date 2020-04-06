package com.matinfard.kitchenordering.utils

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 * Emits event when required. Sate changing and switching between fragments using "back button" while having a shared viewmodel may cause some problems. So, it would be better
 * some observers observe data when required not always. SingleLiveEvent provides this feature.
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer<T> { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }

    @MainThread
    fun call(mValue: T) {
        value = mValue
    }

}