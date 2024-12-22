package com.kryptopass.nooro.core.domain.usecase

import com.kryptopass.nooro.core.domain.services.Logger

class TestLogger : Logger {
    override fun e(
        tag: String,
        message: String,
        throwable: Throwable?
    ) {
        println("[$tag] $message")
        throwable?.printStackTrace()
    }
}
