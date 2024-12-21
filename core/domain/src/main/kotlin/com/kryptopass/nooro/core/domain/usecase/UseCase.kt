package com.kryptopass.nooro.core.domain.usecase

import com.kryptopass.nooro.core.domain.entity.Result
import com.kryptopass.nooro.core.domain.entity.UseCaseException
import com.kryptopass.nooro.core.domain.services.DispatcherProvider
import com.kryptopass.nooro.core.domain.services.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class UseCase<I : UseCase.Request, O : UseCase.Response>(
    private val configuration: Configuration
) {
    fun execute(request: I) = process(request)
        .map {
            Result.Success(it) as Result<O>
        }
        .flowOn(configuration.dispatcher.io)
        .catch { throwable ->
            val useCaseException = UseCaseException.createFromThrowable(throwable)
            configuration.logger.e(TAG, "ERROR WITH USE CASE EXECUTE: ${useCaseException.message}", useCaseException)
            emit(Result.Error(useCaseException))
        }

    internal abstract fun process(request: I): Flow<O>

    class Configuration(
        val dispatcher: DispatcherProvider,
        val logger: Logger
    )

    interface Request

    interface Response

    companion object {
        private val TAG = UseCase::class.java.simpleName
    }
}