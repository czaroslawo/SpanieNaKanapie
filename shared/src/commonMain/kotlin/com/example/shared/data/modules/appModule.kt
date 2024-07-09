package com.example.shared.data.modules

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) = startKoin {
    appDeclaration()
    modules(
        platformModule(),
        authModule()
    )
}

@Suppress("unused") // Used by iOS
fun initKoin() = initKoin {}

