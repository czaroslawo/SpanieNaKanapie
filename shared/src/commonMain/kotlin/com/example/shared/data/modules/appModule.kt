package com.example.shared.data.modules

import com.example.shared.data.storage.SharedSettingsHelper
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {
    appDeclaration()
    modules(
        platformModule(),
        authModule(),
        commonModule()
    )
}

@Suppress("unused") // Used by iOS
fun initKoin() = initKoin {}

fun commonModule() = module {
    single {
        SharedSettingsHelper(
            get(named(SharedSettingsHelper.encryptedSettingsName))
        )
    }
}