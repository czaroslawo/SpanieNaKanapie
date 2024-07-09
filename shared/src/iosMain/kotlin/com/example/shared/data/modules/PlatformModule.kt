package com.example.shared.data.modules


import com.example.shared.data.storage.SharedSettingsHelper
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.component.KoinComponent
import org.koin.core.qualifier.named
import org.koin.dsl.module

@OptIn(ExperimentalSettingsImplementation::class)
actual fun platformModule() = module {
    single<Settings>(named(SharedSettingsHelper.unencryptedSettingsName)) {
        NSUserDefaultsSettings.Factory().create(SharedSettingsHelper.DATABASE_NAME)
    }
    single<Settings>(named(SharedSettingsHelper.encryptedSettingsName)) {
        KeychainSettings(service = SharedSettingsHelper.ENCRYPTED_DATABASE_NAME)
    }
}

object ViewModels : KoinComponent {

}