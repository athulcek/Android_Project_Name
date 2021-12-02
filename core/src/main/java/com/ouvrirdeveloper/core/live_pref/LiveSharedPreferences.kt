package com.ouvrirdeveloper.core.live_pref


import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import kotlinx.coroutines.flow.MutableSharedFlow


class LiveSharedPreferences constructor(private val _preferences: SharedPreferences) {

    //    private val updates = PublishSubject.create<String>()
    private val updates = MutableSharedFlow<String>()

    private val listener = OnSharedPreferenceChangeListener { _, key ->
        updates.tryEmit(key)
    }

    val preferences: SharedPreferences
        get() = _preferences

    init {
        _preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    fun getString(key: String, defaultValue: String?) =
        LivePreference(updates, _preferences, key, defaultValue)

    fun getInt(key: String, defaultValue: Int?) =
        LivePreference(updates, _preferences, key, defaultValue)

    fun getBoolean(key: String, defaultValue: Boolean?) =
        LivePreference(updates, _preferences, key, defaultValue)

    fun getFloat(key: String, defaultValue: Float?) =
        LivePreference(updates, _preferences, key, defaultValue)

    fun getLong(key: String, defaultValue: Long?) =
        LivePreference(updates, _preferences, key, defaultValue)

    fun getStringSet(key: String, defaultValue: Set<String>?) =
        LivePreference(updates, _preferences, key, defaultValue)

    fun <T> listenMultiple(keys: List<String>, defaultValue: T?) =
        MultiPreference(updates, _preferences, keys, defaultValue)
}