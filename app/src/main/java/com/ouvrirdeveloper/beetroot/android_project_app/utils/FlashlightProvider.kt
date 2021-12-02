package com.ouvrirdeveloper.beetroot.android_project_app.utils

import android.content.Context
import android.hardware.Camera
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Build
import com.ouvrirdeveloper.core.utils.PrefUtil


class FlashlightProvider(val context: Context) {

    private val TAG = FlashlightProvider::class.java.simpleName
    private var mCamera: Camera? = null
    private var parameters: Camera.Parameters? = null
    private var camManager: CameraManager? = null
    var flashLight by PrefUtil(PREF_APP_FLASH_LIGHT, false)

    companion object {
        const val PREF_APP_FLASH_LIGHT = "PREF_APP_FLASH_LIGHT"
    }

    fun flashLightListner() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                camManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
                camManager?.apply {
                    getCameraIdList().firstOrNull()?.let { cameraId ->
                        object : CameraManager.TorchCallback() {
                            override fun onTorchModeChanged(cameraId: String, enabled: Boolean) {
                                super.onTorchModeChanged(cameraId, enabled)
                                flashLight = enabled
                            }
                        }
                    }
                }

            } catch (e: CameraAccessException) {

            }
        }
    }

    fun turnFlashlightOn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                camManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
                camManager?.apply {
                    getCameraIdList().get(0)?.let { cameraId ->
                        if(flashLight){
                            flashLight = false
                            setTorchMode(cameraId, false)
                        }else{
                            flashLight = true
                            setTorchMode(cameraId, true)
                        }
                    }
                }

            } catch (e: CameraAccessException) {

            }
        } else {
            /*   mCamera = Camera.open()
               parameters = mCamera.getParameters()
               parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH)
               mCamera.setParameters(parameters)
               mCamera.startPreview()*/
        }
    }

    fun turnFlashlightOff() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                camManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
                camManager?.apply {
                    getCameraIdList().get(0)?.let { cameraId ->
                        flashLight = false
                        setTorchMode(cameraId, false)
                    }
                }
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        } else {
            /*mCamera = Camera.open()
            parameters = mCamera.getParameters()
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF)
            mCamera.setParameters(parameters)
            mCamera.stopPreview()*/
        }
    }
}