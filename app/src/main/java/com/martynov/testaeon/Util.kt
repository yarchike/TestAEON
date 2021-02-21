package com.martynov.testaeon

import java.text.SimpleDateFormat
import java.util.*

fun convecrDateToString(mils: Long): String {
    val date = Date(1582178046)
    val parser = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    val text = parser.format(date)
    return text

}