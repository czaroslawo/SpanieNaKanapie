package com.example.shared.data.utils

import com.example.shared.data.models.City
import com.example.shared.data.models.Post
import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.converter.Converter
import de.jensklingenberg.ktorfit.converter.KtorfitResult
import de.jensklingenberg.ktorfit.converter.TypeData
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

class ResponseConverterFactory : Converter.Factory {

    override fun suspendResponseConverter(
        typeData: TypeData,
        ktorfit: Ktorfit
    ): Converter.SuspendResponseConverter<HttpResponse, *>? {

        // Check if the expected response type is City or Post
        if (typeData.typeInfo.type == City::class || typeData.typeInfo.type == Post::class) {

            return object : Converter.SuspendResponseConverter<HttpResponse, Any> {
                override suspend fun convert(result: KtorfitResult): Resource<Any> {
                    return try {
                        when (result) {
                            is KtorfitResult.Success -> {
                                // Deserialize the response body based on expected type
                                val data = result.response.body<Any>()
                                Resource.Success(data)
                            }
                            is KtorfitResult.Failure -> {
                                Resource.Error(result.throwable.toString())
                            }
                        }
                    } catch (e: Throwable) {
                        Resource.Error(e.toString())
                    }
                }
            }
        }
        return null
    }
}
