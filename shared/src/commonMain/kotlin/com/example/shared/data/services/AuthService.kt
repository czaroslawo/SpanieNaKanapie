package com.example.shared.data.services

import com.example.shared.data.models.LoginParams
import com.example.shared.data.models.RegisterParams
import com.example.shared.data.models.UserToken
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST

interface AuthService {
    @POST("/user")
    suspend fun login(@Body loginParams: LoginParams): UserToken?
    @POST("/register")
    suspend fun register(@Body registerParams: RegisterParams): UserToken
}