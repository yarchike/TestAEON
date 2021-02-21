package com.martynov.testaeon.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martynov.testaeon.R
import com.martynov.testaeon.convecrDateToString
import com.martynov.testaeon.databinding.IteamPaymentsBinding
import com.martynov.testaeon.dto.Payments

class PaymentsAdapter(val listPayments: ArrayList<Payments>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val paymentsView =
                IteamPaymentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentsViewHolder(this, paymentsView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PaymentsViewHolder -> holder.bind(listPayments[position])
        }
    }

    override fun getItemCount(): Int {
        return listPayments.size
    }

    class PaymentsViewHolder(val adapter: PaymentsAdapter, val binding: IteamPaymentsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(payments: Payments) {
            binding.descText.text = payments.desc
            binding.amountText.text = payments.amount.toString()
            binding.currencyText.text = payments.currency
            binding.createdTextView.text = convecrDateToString(payments.created)
        }
    }
}