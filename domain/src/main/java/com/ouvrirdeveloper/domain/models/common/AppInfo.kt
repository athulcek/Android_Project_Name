package com.ouvrirdeveloper.domain.models.common

data class AppInfo(
    var appName: String,
    var icon: Int,
    val packageName: String,
    val spanSize: Int = 1,
    var position: Int,
    var visibility: Boolean = true,
    val onClick: ((Any) -> Unit) = { item ->
        println("====================================================================")
        println("Log from AppInfo {appName=$appName},{packageName=$packageName} ${item}")
        println("====================================================================")
    }
)