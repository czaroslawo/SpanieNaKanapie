package com.example.shared.data.repositories

import com.example.shared.data.models.LoginParams
import com.example.shared.data.models.RegisterParams
import com.example.shared.data.models.UserToken
import com.example.shared.data.services.AuthService
import com.example.shared.data.storage.SharedSettingsHelper
import com.example.shared.data.utils.Resource
import io.ktor.client.HttpClient
import io.ktor.utils.io.errors.EOFException
import io.ktor.utils.io.errors.IOException

class AuthRepositoryImpl(
    private val authService: AuthService,
    private val sharedSettingsHelper: SharedSettingsHelper
) : AuthRepository {
    override suspend fun login(loginParams: LoginParams): Resource<Unit> {
        return try {
            val result = authService.login(loginParams)
            sharedSettingsHelper.authToken = result?.authToken
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: EOFException) {
            Resource.Error("${e.message}")
        } catch (e: Exception) {
            Resource.Error("${e.message}")

        }
    }


    override suspend fun register(registerParams: RegisterParams): Resource<Unit> {
        return try {
            val result = authService.register(registerParams)
            sharedSettingsHelper.authToken = result.authToken
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error("${e.message}")
        } catch (e: EOFException) {
            Resource.Error("${e.message}")
        } catch (e: Exception) {
            Resource.Error("${e.message}")

        }
    }

}