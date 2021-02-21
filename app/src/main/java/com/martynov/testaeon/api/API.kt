package com.martynov.testaeon.api

import com.martynov.testaeon.dto.AuthRequest
import com.martynov.testaeon.dto.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface API {
    @POST("login")
    suspend fun authenticate(@Body authRequestParams: AuthRequest): Response<AuthResponse>
}