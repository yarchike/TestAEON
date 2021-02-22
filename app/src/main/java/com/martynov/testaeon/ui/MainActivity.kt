package com.martynov.testaeon.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import com.martynov.testaeon.API_SHARED_FILE
import com.martynov.testaeon.AUTHENTICATED_SHARED_KEY
import com.martynov.testaeon.App
import com.martynov.testaeon.R
import com.martynov.testaeon.databinding.ActivityMainBinding
import com.martynov.testaeon.dto.AuthRequest
import kotlinx.coroutines.launch
import java.io.IOException


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        if (isAuthenticated()) {
            navigateToFeed()
        }
        binding.btnLogin.setOnClickListener {
            toAutch()
        }

    }

    private fun toAutch() {
        val login = binding.loginText.text.toString()
        val password = binding.passwordText.text.toString()
        when {
            login == "" -> {
                binding.textInputLogin.error = "Не введен логин"
            }
            password == "" -> {
                binding.textInputPassword.error = "Не введен пароль"
            }
            else -> {
                lifecycleScope.launch {
                    try {
                        val response = App.repository.authenticate(AuthRequest(login, password))
                        if (response.body()?.success.equals("true")) {
                            response.body()?.response?.token?.let { setUserAuth(it) }
                            navigateToFeed()
                        } else {
                            Toast.makeText(
                                    this@MainActivity,
                                    "Неверный логин или пароль",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: IOException) {
                        Toast.makeText(
                                this@MainActivity,
                                "Ошибка соединения с сервером",
                                Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }

        }
    }

    private fun setUserAuth(token: String) =
            getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE)
                    .edit()
                    .putString(AUTHENTICATED_SHARED_KEY, token)
                    .apply()

    private fun isAuthenticated(): Boolean =
            getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE).getString(
                    AUTHENTICATED_SHARED_KEY, ""
            )?.isNotEmpty() ?: false

    private fun navigateToFeed() {
        val intent = Intent(this@MainActivity, FeedActivity::class.java)
        startActivity(intent)
        finish()
    }
}