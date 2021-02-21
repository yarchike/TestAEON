package com.martynov.testaeon.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.martynov.testaeon.API_SHARED_FILE
import com.martynov.testaeon.AUTHENTICATED_SHARED_KEY
import com.martynov.testaeon.App
import com.martynov.testaeon.R
import kotlinx.coroutines.launch

class FeedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        val token = getToken()
        lifecycleScope.launch {
            val result = token?.let { App.repository.getPayments(it) }
        }
    }

    private fun getToken(): String? =
            getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE).getString(
                    AUTHENTICATED_SHARED_KEY, "")
}