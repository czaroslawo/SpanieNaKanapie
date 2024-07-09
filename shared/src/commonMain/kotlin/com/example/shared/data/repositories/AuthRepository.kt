package com.example.shared.data.repositories

import com.example.shared.data.models.LoginParams
import com.example.shared.data.models.RegisterParams
import com.example.shared.data.models.UserToken
import com.example.shared.data.utils.Resource

interface AuthRepository {
    suspend fun login(loginParams: LoginParams): Resource<Unit>

    suspend fun register(registerParams: RegisterParams): Resource<Unit>
}