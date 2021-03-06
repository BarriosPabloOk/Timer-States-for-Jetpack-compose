package com.pablobarriosdevs.stopwatchorcountdown.formatter
/*Copyright (C) 2022  Pablo Barrios

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
import android.os.Build
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class Formatter {


    companion object{
        fun Long.hours(): Long = (this / 1000 % 60) / 3600
        fun Long.minutes(): Long = this / 1000 / 60
        fun Long.seconds(): Long = this / 1000 % 60
        fun Long.millis(): Long = this % 1000
        fun formatTime(time: Long, pattern: Patterns): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val l = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(time),
                    ZoneId.of("Etc/GMT+0")
                )
                val format = DateTimeFormatter.ofPattern(pattern.pattern, Locale.getDefault())
                format.format(l)
            } else {
                val format = SimpleDateFormat(pattern.pattern, Locale.getDefault()).format(Date(time))
                format
            }
        }
    }


}
