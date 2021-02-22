package com.martynov.testaeon.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martynov.testaeon.R
import com.martynov.testaeon.convecrDateToString
import com.martynov.testaeon.databinding.IteamPaymentsBinding
import com.martynov.testaeon.dto.Payments

class PaymentsAdapter(val listPayments: ArrayList<Payments>, private val onClick: (position: Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val paymentsView =
                IteamPaymentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentsViewHolder(this, paymentsView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PaymentsViewHolder -> holder.bind(listPayments[position], position)
        }
    }

    override fun getItemCount(): Int {
        return listPayments.size
    }

    inner class PaymentsViewHolder(val adapter: PaymentsAdapter, val binding: IteamPaymentsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(payments: Payments, posit: Int) {
            with(itemView) {
                if (payments.desc != "") {
                    val finaldesc = payments.desc.replace(Regex("[^0-9]"), "")
                    binding.descText.text = "${context.getString(R.string.operation)} $finaldesc"

                } else {
                    binding.descText.text = context.getString(R.string.no_data)
                }
                if (payments.amount.toString() != "") {
                    binding.amountText.text = String.format("%.2f", payments.amount)
                } else {
                    binding.amountText.text = context.getString(R.string.no_data)
                }
                if (payments.currency != null && payments.currency != "") {
                    Log.d("My", payments.currency)
                    binding.currencyText.text = payments.currency
                } else {
                    binding.currencyText.text = context.getString(R.string.no_data)
                }
                if (convecrDateToString(payments.created) != "") {
                    binding.createdTextView.text = convecrDateToString(payments.created)
                } else {
                    binding.createdTextView.text = context.getString(R.string.no_data)
                }
                this.setOnClickListener {
                    onClick(posit)
                }
            }


        }

    }
}