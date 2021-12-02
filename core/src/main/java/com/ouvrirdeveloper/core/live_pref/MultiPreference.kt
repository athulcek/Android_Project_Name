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
class MultiPreference<T> constructor(
    private val updates: Flow<String>,
    private val preferences: SharedPreferences,
    private val keys: List<String>,
    private val defaultValue: T?
) : MutableLiveData<Map<String, T?>>() {

    //    private var disposable: Disposable? = null
    private val values = mutableMapOf<String, T?>()

    init {
        for (key in keys) {
            values[key] = (preferences.all[key] as T) ?: defaultValue
        }

        value = values
    }

    override fun onActive() {
        super.onActive()

        CoroutineScope(Dispatchers.IO).launch {
            updates.filter { t ->
                keys.contains(t)
            }.collect {
                values[it] = (preferences.all[it] as T) ?: defaultValue
                postValue(values)
            }
        }
        /* disposable = updates
             .filter { t -> keys.contains(t) }
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe {
                 values[it] = (preferences.all[it] as T) ?: defaultValue
                 postValue(values)
             }*/
    }

    override fun onInactive() {
        super.onInactive()
        //   disposable?.dispose()
    }
}