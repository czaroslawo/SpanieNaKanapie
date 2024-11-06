package com.example.shared.data.modules

import com.example.shared.data.repositories.PostRepository
import com.example.shared.data.repositories.PostRepositoryImpl
import com.example.shared.data.services.PostService
import com.example.shared.data.utils.Constants
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

fun postModule() = module{
    val ktorClient = HttpClient(){
        install(ContentNegotiation) {
            json()
        }
    }
    val ktorfit = Ktorfit.Builder().baseUrl(Constants.API_URL).httpClient(ktorClient).build()
    single{ktorfit.create<PostService>()}
    single<PostRepository>{ PostRepositoryImpl(get(), get()) }
}