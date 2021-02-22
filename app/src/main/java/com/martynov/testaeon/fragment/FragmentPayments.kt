package com.martynov.testaeon.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.martynov.testaeon.R

class FragmentPayments : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = this.arguments
        val desc = bundle?.getString("desc")
        val amount = bundle?.getString("amount")
        val currency = bundle?.getString("currency")
        val created = bundle?.getString("created")
        val viewPayments = inflater.inflate(R.layout.fragemnt_payments, null)

        if (desc != "") {
            val finaldesc = desc?.replace(Regex("[^0-9]"), "")
            viewPayments.findViewById<TextView>(R.id.descText).text = "${context?.getString(com.martynov.testaeon.R.string.operation)} $finaldesc"

        } else {
            viewPayments.findViewById<TextView>(R.id.descText).text = context?.getString(com.martynov.testaeon.R.string.no_data)
        }
        if (amount != "") {
            viewPayments.findViewById<TextView>(R.id.amountText).text = kotlin.String.format("%.2f", amount?.toDouble())
        } else {
            viewPayments.findViewById<TextView>(R.id.amountText).text = context?.getString(com.martynov.testaeon.R.string.no_data)
        }
        if (currency != null && currency != "") {
            viewPayments.findViewById<TextView>(R.id.currencyText).text = currency
        } else {
            viewPayments.findViewById<TextView>(R.id.currencyText).text = context?.getString(com.martynov.testaeon.R.string.no_data)
        }
        if (created != "") {
            viewPayments.findViewById<TextView>(R.id.createdTextView).text = created
        } else {
            viewPayments.findViewById<TextView>(R.id.createdTextView).text = context?.getString(com.martynov.testaeon.R.string.no_data)
        }
        viewPayments.findViewById<TextView>(R.id.moreText).text = "Ту должна быть подробная иформация о платеже"





        return viewPayments
    }
}