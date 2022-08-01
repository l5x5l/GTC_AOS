package com.softsquared.gridge_test.android.instagram_challenge.utils

import android.content.Context
import com.softsquared.gridge_test.android.instagram_challenge.R
import java.text.SimpleDateFormat
import java.util.*

const val minuteSecond = 60L
const val hourSecond = minuteSecond * 60L
const val daySecond = hourSecond * 24L
const val monthSecond = daySecond * 30L

/**
 * getString 을 사용해 string 리소스를 가져오기 때문에, context 가 필요합니다
 */
fun getTimeDiffFromCurrent(createAt : String, context : Context) : String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val createAtDate = dateFormat.parse(createAt)
    if (createAtDate != null) {
        val currentDate = Date()
        val timeDiff = (currentDate.time - createAtDate.time) / 1000
        val timeDiffText = if (timeDiff < minuteSecond) {
            context.getString(R.string.format_before_second, timeDiff)
        } else if (timeDiff < hourSecond) {
            context.getString(R.string.format_before_minute, timeDiff / minuteSecond)
        } else if (timeDiff < daySecond) {
            context.getString(R.string.format_before_hour, timeDiff / hourSecond)
        } else if (timeDiff < monthSecond) {
            context.getString(R.string.format_before_day, timeDiff / daySecond)
        } else {
            val calendar = Calendar.getInstance()
            calendar.time = createAtDate
            context.getString(R.string.format_date, calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH))
        }
        return timeDiffText
    } else {
        return "time parse error...."
    }
}