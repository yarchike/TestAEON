package com.martynov.testaeon.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.martynov.testaeon.*
import com.martynov.testaeon.adapter.PaymentsAdapter
import com.martynov.testaeon.databinding.ActivityFeedBinding
import com.martynov.testaeon.dto.Payments
import com.martynov.testaeon.fragment.FragmentPayments
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
        binding.swipeContainer.setOnRefreshListener {
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
        val token = getToken()
        lifecycleScope.launch {
            val result = token?.let { App.repository.getPayments(it) }
            items = result?.body()?.response!!
            with(binding.container) {
                layoutManager = LinearLayoutManager(this@FeedActivity)
                adapter = PaymentsAdapter(items, ::onItemClick)
                binding.swipeContainer.isRefreshing = false
            }
        }
    }

    private fun getToken(): String? =
            getSharedPreferences(API_SHARED_FILE, Context.MODE_PRIVATE).getString(
                    AUTHENTICATED_SHARED_KEY, "")

    fun onItemClick(position: Int) {
        val item = items.get(position)
        val bundle = Bundle()
        bundle.putString(
                "desc", item.desc
        )
        bundle.putString(
                "amount", item.amount.toString()
        )
        bundle.putString("currency", item.currency)
        bundle.putString("created", convecrDateToString(item.created))

        val frt: FragmentTransaction = supportFragmentManager.beginTransaction()
        val fragment: FragmentPayments = FragmentPayments()
        fragment.arguments = bundle
        frt.addToBackStack(null)
        frt.add(R.id.itemFragmentPayments, fragment)
        frt.commit()
    }
}