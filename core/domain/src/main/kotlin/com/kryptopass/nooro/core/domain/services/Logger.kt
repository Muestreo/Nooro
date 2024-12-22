package com.kryptopass.nooro.core.domain.services

interface Logger {
    fun e(tag: String, message: String, throwable: Throwable? = null)
}

class NoOpLogger : Logger {
    override fun e(tag: String, message: String, throwable: Throwable?) {
        // No-op
    }
}
