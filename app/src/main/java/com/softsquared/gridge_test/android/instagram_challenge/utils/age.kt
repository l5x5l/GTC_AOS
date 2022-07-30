package com.softsquared.gridge_test.android.instagram_challenge.utils

import java.util.*
import kotlin.math.max

fun calcKoreanAge(birthYear : Int) : Int {
    return max(Calendar.getInstance().get(Calendar.YEAR) - birthYear + 1, 0)
}

fun checkAvailableDate(birthYear : Int, birthMonth : Int, birthDay : Int) : Boolean {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH) + 1
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

    return ("${birthYear}${String.format("%02d", birthMonth)}${String.format("%02d", birthDay)}" <= "${currentYear}${String.format("%02d", currentMonth)}${String.format("%02d", currentDay)}")
}