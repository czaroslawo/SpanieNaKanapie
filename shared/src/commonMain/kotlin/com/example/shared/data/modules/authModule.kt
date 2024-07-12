package com.example.shared.data.modules

import com.example.shared.data.repositories.AuthRepository
import com.example.shared.data.repositories.AuthRepositoryImpl
import com.example.shared.data.services.AuthService
import com.example.shared.data.utils.Constants
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module


fun authModule() = module {
    val ktorfit = Ktorfit.Builder().baseUrl(Constants.API_URL).httpClient(
        HttpClient {
            install(ContentNegotiation) {
                json(Json)
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = object : io.ktor.client.plugins.logging.Logger {
                    override fun log(message: String) {
                        co.touchlab.kermit.Logger.d(message, null, "HTTP CLIENT")
                    }
                }
            }
            install(DefaultRequest) {
                header(
                    io.ktor.http.HttpHeaders.ContentType,
                    io.ktor.http.ContentType.Application.Json
                )
                header(io.ktor.http.HttpHeaders.Accept, io.ktor.http.ContentType.Application.Json)
            }
        }).build()
    single { ktorfit.create<AuthService>() }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}