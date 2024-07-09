package com.example.shared.data.storage

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class SharedSettingsHelper(
    private val encryptedSettings: Settings,
) {
    var authToken: String?
        get() {
            return encryptedSettings[AUTH_TOKEN_NAME]
        }
        set(value) {
            authTokenListener?.invoke(value)
            encryptedSettings[AUTH_TOKEN_NAME] = value
        }

    private var authTokenListener: ((token: String?) -> Unit)? = null
    fun setAuthTokenListener(listener: ((token: String?) -> Unit)?) {
        authTokenListener = listener
    }

    companion object {
        const val DATABASE_NAME = "UNENCRYPTED_SETTINGS"
        const val ENCRYPTED_DATABASE_NAME = "ENCRYPTED_SETTINGS"
        const val encryptedSettingsName = "encryptedSettings"
        const val unencryptedSettingsName = "unencryptedSettings"
        const val AUTH_TOKEN_NAME = "AUTH_TOKEN"
    }
}