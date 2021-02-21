package com.martynov.testaeon.api

import com.martynov.testaeon.dto.AuthRequest
import com.martynov.testaeon.dto.AuthResponse
import com.martynov.testaeon.dto.PaymentsResponse
import retrofit2.Response
import retrofit2.http.*

interface API {
    @POST("login")
    suspend fun authenticate(@Body authRequestParams: AuthRequest): Response<AuthResponse>
    @GET("payments")
    suspend fun getPayments(@Query("token") token: String): Response<PaymentsResponse>


}