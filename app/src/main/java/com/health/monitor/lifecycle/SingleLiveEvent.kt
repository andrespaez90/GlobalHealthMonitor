package com.health.monitor.lifecycle

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleLiveEvent<T> : MutableLiveData<T>() {
    private var lastHandledItem: T? = null

    fun observeSingleEvent(owner: LifecycleOwner, observer: (T?) -> Unit) {
        this.observe(owner, Observer {
            if (it != lastHandledItem) {
                observer(it)
                lastHandledItem = it
            }
        })
    }
}