package com.ouvrirdeveloper.beetroot.android_project_app.features.home

import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import android.view.View
import com.ouvrirdeveloper.beetroot.R
import com.ouvrirdeveloper.beetroot.android_project_app.features.home.epoxy.HomeController
import com.ouvrirdeveloper.beetroot.databinding.FragmentHomeBinding
import com.ouvrirdeveloper.core.ui.BaseFragmentWithBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment :
    BaseFragmentWithBinding<FragmentHomeBinding>(R.layout.fragment_home) {

    private val viewModel by sharedViewModel<HomeViewModel>()
    lateinit var controller: HomeController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setObservers()
        checkBattery()
    }

    private fun checkBattery() {
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            requireContext().registerReceiver(null, ifilter)
        }
        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                || status == BatteryManager.BATTERY_STATUS_FULL

// How are we charging?
        val chargePlug: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) ?: -1
        val usbCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_USB
        val acCharge: Boolean = chargePlug == BatteryManager.BATTERY_PLUGGED_AC
        val batteryPct: Float? = batteryStatus?.let { intent ->
            val level: Int = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale: Int = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale.toFloat()
        }

        viewModel.batteryInfo.postValue(
            viewModel.defaultItems.get(0).also {
                it.appName = "$batteryPct"
            }
        )
    }

    private fun setRecyclerView() {
        controller = HomeController(requireContext(), { reportType, uniqueId ->

            when (reportType) {
            }
        })
        binding.rvHome.setControllerAndBuildModels(controller)
    }

    private fun setObservers() {
        viewModel.topIcons.observe(viewLifecycleOwner, controller::doForTopIcons)
    }

}

