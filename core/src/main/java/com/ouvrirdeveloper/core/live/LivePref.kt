package com.ouvrirdeveloper.core.live

import android.content.SharedPreferences
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class LivePref<T> constructor(
    private val updates: Flow<String>,
    private val preferences: SharedPreferences,
    private val key: String,
    private val defaultValue: T
) : MutableStateFlow<T> {

    private var lastValue: T? = null

    init {
        lastValue = (preferences.all[key] as T) ?: defaultValue

    }


    @InternalCoroutinesApi
    override suspend fun collect(collector: FlowCollector<T>) {
        collector.emit(lastValue ?: defaultValue)
    }

    override val subscriptionCount: StateFlow<Int>
        get() = subscriptionCount

    override suspend fun emit(value: T) {

    }

    @ExperimentalCoroutinesApi
    override fun resetReplayCache() {
        TODO("Not yet implemented")
    }

    override fun tryEmit(value: T): Boolean {
        TODO("Not yet implemented")
    }

    override var value: T
        get() = lastValue ?: defaultValue
        set(value) {
            lastValue = value
        }

    override fun compareAndSet(expect: T, update: T): Boolean {
        if (expect != null) {
            return expect.equals(update)
        }
        return false
    }

    override val replayCache: List<T>
        get() = replayCache

}