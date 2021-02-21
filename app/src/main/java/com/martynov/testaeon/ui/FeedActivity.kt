package com.martynov.testaeon.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.martynov.testaeon.API_SHARED_FILE
import com.martynov.testaeon.AUTHENTICATED_SHARED_KEY
import com.martynov.testaeon.App
import com.martynov.testaeon.R
import com.martynov.testaeon.adapter.PaymentsAdapter
import com.martynov.testaeon.databinding.ActivityFeedBinding
import com.martynov.testaeon.databinding.ActivityMainBinding
import com.martynov.testaeon.dto.Payments
import kotlinx.coroutines.launch

class FeedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    var items = ArrayList<Payments>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))
        loadPay()
        binding.swipeContainer.setOnRefreshListener{
            loadPay()


        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_exit -> {
                getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE)
                        .edit()
                        .remove(AUTHENTICATED_SHARED_KEY)
                        .apply()
                val intent = Intent(this@FeedActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun loadPay() {
        var items1 = items
        val token = getToken()
        lifecycleScope.launch {
            val result = token?.let { App.repository.getPayments(it) }
            items1 = result?.body()?.response!!
            with(binding.container) {
                layoutManager = LinearLayoutManager(this@FeedActivity)
                adapter = PaymentsAdapter(items1)
                binding.swipeContainer.isRefreshing = false
            }
        }
    }

    private fun getToken(): String? =
            getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE).getString(
                    AUTHENTICATED_SHARED_KEY, "")
}