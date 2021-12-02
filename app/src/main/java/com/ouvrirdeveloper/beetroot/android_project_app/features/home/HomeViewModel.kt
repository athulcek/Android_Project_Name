package com.ouvrirdeveloper.beetroot.android_project_app.features.home

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.pm.ResolveInfo
import android.os.Build
import android.provider.Settings
import androidx.lifecycle.MutableLiveData
import com.ouvrirdeveloper.basearc.ui.base.BaseViewModel
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.beetroot.android_project_app.utils.FlashlightProvider
import com.ouvrirdeveloper.core.live_pref.LiveSharedPreferences
import com.ouvrirdeveloper.core.utils.PrefUtil
import com.ouvrirdeveloper.domain.models.common.AppInfo


class HomeViewModel(val context: Context) : BaseViewModel() {

    val applications = MutableLiveData<List<AppInfo>>()
    val defaultItems = listOf<AppInfo>(
        AppInfo(
            appName = "battery_stat",
            icon = R.drawable.ic_battery_std,
            packageName = "dfg",
            position = 0,
            onClick = {
                openBatterySettings()
            }
        ),
        AppInfo(
            appName = "flash",
            icon = R.drawable.ic_flash_off,
            packageName = "dfg",
            position = 1,
            onClick = {
                toggleFlashLight()
            }

        ),
        AppInfo(
            appName = "ring_volume",
            icon = R.drawable.ic_volume_down,
            packageName = "dfg",
            position = 2,
        )
    )

    private fun toggleFlashLight() {
        val flash = FlashlightProvider(context)
        flash.turnFlashlightOn()
    }

    private fun openBatterySettings() {
        if (Build.MANUFACTURER == "samsung") {
            val intent = Intent()
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                intent.component = ComponentName(
                    "com.samsung.android.lool",
                    "com.samsung.android.sm.ui.battery.BatteryActivity"
                )
            } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                intent.component = ComponentName(
                    "com.samsung.android.sm",
                    "com.samsung.android.sm.ui.battery.BatteryActivity"
                )
            }

            try {
                context.startActivity(intent);
            } catch (ex: ActivityNotFoundException) {
                // Fallback to global settings
                val intent = Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS)
                intent.flags = FLAG_ACTIVITY_NEW_TASK
                context.startActivity(
                    intent
                )
            }
        } else {
            val intent = Intent(Settings.ACTION_BATTERY_SAVER_SETTINGS)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    val topIcons = MutableLiveData<List<AppInfo>>()
    val batteryInfo = MutableLiveData<AppInfo>()
    val flashlightProvider = FlashlightProvider(context)
    val liveSharedPreferences = LiveSharedPreferences(PrefUtil.getPref())

    init {
        liveSharedPreferences.getBoolean(FlashlightProvider.PREF_APP_FLASH_LIGHT, false)
            .observeForever { enabled ->

                defaultItems.get(1).apply {
                    if (enabled)
                        icon = R.drawable.ic_flash_on
                    else
                        icon = R.drawable.ic_flash_off
                }
                refreshTopIcons()
            }
        flashlightProvider.flashLightListner()
        val resolvedApplist: List<ResolveInfo> = context.packageManager
            .queryIntentActivities(
                Intent(Intent.ACTION_MAIN, null)
                    .addCategory(Intent.CATEGORY_LAUNCHER), 0
            )

        val appList = mutableListOf<AppInfo>()
        resolvedApplist.sortedBy { it.loadLabel(context.packageManager).toString() }
            .forEachIndexed { index, ri ->
                if (ri.activityInfo.packageName != context.packageName) {
                    val app = AppInfo(
                        appName = ri.loadLabel(context.packageManager).toString(),
                        icon = ri.activityInfo.iconResource,
                        packageName = ri.activityInfo.packageName,
                        position = index
                    )
                    appList.add(app)
                }
            }
        applications.postValue(appList)


        batteryInfo.observeForever {
            topIcons.postValue(defaultItems)
        }
    }

    private fun refreshTopIcons() {
        topIcons.postValue(defaultItems)
    }


}