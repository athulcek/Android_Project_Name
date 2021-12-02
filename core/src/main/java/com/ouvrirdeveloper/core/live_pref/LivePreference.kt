package com.ouvrirdeveloper.core.live_pref

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class LivePreference<T> constructor(
    private val updates: Flow<String>,
    private val preferences: SharedPreferences,
    private val key: String,
    private val defaultValue: T?
) : MutableLiveData<T>() {

    private var lastValue: T? = null

    init {
        lastValue = (preferences.all[key] as T) ?: defaultValue
        value = lastValue
    }

    override fun onActive() {
        super.onActive()

        if (preferences.all.containsKey(key)) {
            if (lastValue != preferences.all[key]) {
                lastValue = preferences.all[key] as T
            }
        }
        postValue(lastValue)


        CoroutineScope(Dispatchers.IO).launch {
            updates.filter { t -> t == key }.collect {
                postValue((preferences.all[it] as T) ?: defaultValue)
            }
        }


        /*coroutineScope = updates
            .filter { t -> t == key }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                postValue((preferences.all[it] as T) ?: defaultValue)
            }*/
    }

    override fun onInactive() {
        super.onInactive()
//        coroutineScope?.dispose()
    }
}