package com.martynov.testaeon.repository

import com.martynov.testaeon.api.API
import com.martynov.testaeon.dto.AuthRequest
import com.martynov.testaeon.dto.AuthResponse
import com.martynov.testaeon.dto.PaymentsResponse
import retrofit2.Response

class NetworkRepository(private val api: API): Repository {
    override suspend fun authenticate(authRequestParams: AuthRequest): Response<AuthResponse> {
        return api.authenticate(authRequestParams)
    }

    override suspend fun getPayments(token: String): Response<PaymentsResponse> {
        return api.getPayments(token)
    }

}