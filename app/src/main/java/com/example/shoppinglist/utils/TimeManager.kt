package com.example.shoppinglist.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeManager {
    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("d MMM yyyy, EEE, HH:mm:ss z", Locale.getDefault())
        return dateFormat.format(Date())
    }
}