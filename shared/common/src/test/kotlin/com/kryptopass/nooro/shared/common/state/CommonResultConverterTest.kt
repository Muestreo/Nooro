package com.kryptopass.nooro.shared.common.state

import com.kryptopass.nooro.core.domain.entity.Result
import com.kryptopass.nooro.core.domain.entity.UseCaseException
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@CommonModuleTests
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class CommonResultConverterTests
class CommonResultConverterTest {

    private val converter = object : CommonResultConverter<String, String>() {
        override fun convertSuccess(data: String): String {
            return "result${data}"
        }
    }

    @Test
    fun testConvertError() {
        val errorMessage = "errorMessage"
        val exception = mock<UseCaseException.WeatherException>()

        whenever(exception.localizedMessage).thenReturn(errorMessage)
        val errorResult = Result.Error(exception)
        val result = converter.convert(errorResult)

        assertEquals(UiState.Error(errorMessage), result)
    }

    @Test
    fun testConvertSuccess() {
        val data = "data"
        val successResult = Result.Success(data)
        val result = converter.convert(successResult)

        assertEquals(UiState.Success("result${data}"), result)
    }
}
