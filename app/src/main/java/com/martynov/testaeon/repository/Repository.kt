package com.martynov.testaeon.repository

import com.martynov.testaeon.dto.AuthRequest
import com.martynov.testaeon.dto.AuthResponse
import com.martynov.testaeon.dto.PaymentsResponse
import retrofit2.Response

interface Repository {
    suspend fun authenticate(authRequestParams: AuthRequest): Response<AuthResponse>
    suspend fun getPayments(token: String): Response<PaymentsResponse>
}