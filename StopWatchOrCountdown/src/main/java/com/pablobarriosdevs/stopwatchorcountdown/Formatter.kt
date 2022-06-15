package com.pablobarriosdevs.stopwatchorcountdown

import android.os.Build
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

enum class Formatter(val defaultState: String, val pattern: String) {

    MM_SS(defaultState = "00:00", pattern = "mm:ss"),
    MM_SS_SS(defaultState = "00:00.00", pattern = "mm:ss.SS"),
    HH_MM_SS(defaultState = "00:00:00", pattern = "HH:mm:ss"),
    HH_MM_SS_SS(defaultState = "00:00:00.00", pattern = "HH:mm:ss.SS");


    companion object {
        fun Long.hours(): Long = (this / 1000 % 60) / 3600
        fun Long.minutes(): Long = this / 1000 / 60
        fun Long.seconds(): Long = this / 1000 % 60
        fun Long.millis(): Long = this % 1000

        fun formatTime(time: Long, pattern: Formatter): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val l = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(time),
                    ZoneId.of("Etc/GMT+0")
                )
                val format = DateTimeFormatter.ofPattern(pattern.pattern)
                format.format(l)
            } else {
                val format = SimpleDateFormat(pattern.pattern).format(Date(time))
                format
            }
        }
    }
}
