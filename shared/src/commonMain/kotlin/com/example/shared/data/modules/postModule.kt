package com.example.shared.data.modules

import com.example.shared.data.repositories.PostRepository
import com.example.shared.data.repositories.PostRepositoryImpl
import com.example.shared.data.services.PostService
import com.example.shared.data.storage.SharedSettingsHelper
import com.example.shared.data.utils.Constants
import com.example.shared.data.utils.ResponseConverterFactory
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

fun postModule() = module{

        single<PostRepository>{ PostRepositoryImpl(get()) }
        single{
            val authToken = get<SharedSettingsHelper>().authToken
            val ktorClient = HttpClient() {
                install(ContentNegotiation) {
                    json()
                }
                install(DefaultRequest) {
                    header(
                        HttpHeaders.ContentType,
                        io.ktor.http.ContentType.Application.Json
                    )
                    header(HttpHeaders.Accept, io.ktor.http.ContentType.Application.Json)
                    header(
                        HttpHeaders.Authorization,
                        "Bearer $authToken"
                    )

                }
                install(Logging) {
                    level = LogLevel.ALL
                    logger = object : io.ktor.client.plugins.logging.Logger {
                        override fun log(message: String) {
                            co.touchlab.kermit.Logger.d(message, null, "HTTP CLIENT")
                        }
                    }
                }
            }

            val ktorfit = Ktorfit.Builder().baseUrl(Constants.API_URL).httpClient(ktorClient).converterFactories(ResponseConverterFactory()).build()
            ktorfit.create<PostService>()
        }




}