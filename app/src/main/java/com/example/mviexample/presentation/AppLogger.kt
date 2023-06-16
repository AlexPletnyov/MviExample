package com.example.mviexample.presentation

import android.util.Log

class AppLogger {
    private var className = UNKNOWN_CLASS

    fun connect(className: Class<*>) {
        Log.i("TEST1", "Connect to ${className.simpleName}")
        this.className = className.simpleName
    }

    fun connect(methodName: String) {
        Log.i("TEST1", "Connect to $methodName")
        className = methodName
    }

    fun d(msg: String) {
        Log.d(TAG_IP_WEB_LOGGER + className, msg)
    }

    fun e(msg: String) {
        Log.e(TAG_IP_WEB_LOGGER + className, msg)
    }

    private companion object {
        const val UNKNOWN_CLASS = "UnknownClass"
        const val TAG_IP_WEB_LOGGER = "APP LOGGER | "
    }
}