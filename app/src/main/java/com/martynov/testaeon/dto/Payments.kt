package com.martynov.testaeon.dto

data class Payments(
        val desc: String,
        val amount: Double,
        val currency: String,
        val created: Int
)
