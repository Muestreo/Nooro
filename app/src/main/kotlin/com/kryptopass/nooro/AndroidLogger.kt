package com.kryptopass.nooro

import android.util.Log
import com.kryptopass.nooro.core.domain.services.Logger

class AndroidLogger : Logger {
    override fun e(tag: String, message: String, throwable: Throwable?) {
        Log.e(tag, message, throwable)
    }
}