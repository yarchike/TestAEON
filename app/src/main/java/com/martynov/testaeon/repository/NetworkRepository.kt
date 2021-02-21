package com.martynov.testaeon.repository

import com.martynov.testaeon.api.API
import com.martynov.testaeon.dto.AuthRequest
import com.martynov.testaeon.dto.AuthResponse
import retrofit2.Response

class NetworkRepository(private val api: API): Repository {
    override suspend fun authenticate(authRequestParams: AuthRequest): Response<AuthResponse> {
        return api.authenticate(authRequestParams)
    }

}