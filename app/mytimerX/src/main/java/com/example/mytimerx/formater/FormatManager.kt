package com.example.mytimerx.formater

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


class FormatManager {

    // Method that accepts both time in millis and LocalDateTime as nullable
    // Format time either from milliseconds or LocalDateTime
    fun formatTime(timeInMillis: Long? = null, dateTime: Date? = null,
                   format: String, locale: Locale = Locale.getDefault()): String {
        val resolvedDateTime = when {
            dateTime != null -> dateTime                     // Use Date if provided
            timeInMillis != null -> Date(timeInMillis)      // Use timeInMillis if provided
            else -> Date()                                   // If both are null, use the current time
        }

        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(resolvedDateTime)
    }

    // Convert time in millis to a formatted string based on a specific format
    fun convertMillisToFormattedString(timeInMillis: Long, format: String, locale: Locale = Locale.getDefault()): String {
        val date = Date(timeInMillis)
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(date)
    }


    // Get current time in 24-hour format
    fun getCurrentTime24HourFormat(): String {
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return outputFormat.format(Date())
    }

    // Get current time in 12-hour format
    fun getCurrentTime12HourFormat(): String {
        val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return outputFormat.format(Date())
    }

    // Convert a timestamp to a formatted date string
    fun formatTimestamp(timestamp: Long, pattern: String = "dd/MM/yyyy HH:mm"): String {
        val dateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return dateFormat.format(Date(timestamp))
    }

    // Calculate the difference between two time strings in HH:mm format
    fun timeDifference(time1: String, time2: String): String {
        return try {
            val format = SimpleDateFormat("HH:mm", Locale.getDefault())
            val date1 = format.parse(time1)
            val date2 = format.parse(time2)

            val diffInMillis = Math.abs(date2.time - date1.time)
            val hours = (diffInMillis / (1000 * 60 * 60)).toInt()
            val minutes = (diffInMillis / (1000 * 60) % 60).toInt()
            String.format(Locale.getDefault(), "%02d:%02d", hours, minutes)
        } catch (e: ParseException) {
            "Invalid time format"
        }
    }

    // Validate if a given time string is in the correct format (HH:mm)
    fun isValidTimeFormat(time: String): Boolean {
        return try {
            val format = SimpleDateFormat("HH:mm", Locale.getDefault())
            format.parse(time)
            true
        } catch (e: ParseException) {
            false
        }
    }

    // Convert various time formats to a unified format
    fun parseTimeToStandardFormat(time: String): String {
        return when {
            time.matches(Regex("\\d{2}:\\d{2}")) -> time // Already in HH:mm format
            time.matches(Regex("\\d{1,2}:\\d{2} (AM|PM)")) -> {
                val inputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
                val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                outputFormat.format(inputFormat.parse(time) ?: Date())
            }
            else -> "Invalid time format"
        }
    }

    // Convert time between different time zones
    fun convertTimeZone(time: String, fromZone: TimeZone, toZone: TimeZone): String {
        return try {
            val format = SimpleDateFormat("HH:mm", Locale.getDefault())
            format.timeZone = fromZone
            val date = format.parse(time)

            format.timeZone = toZone
            format.format(date ?: Date())
        } catch (e: ParseException) {
            "Invalid time format"
        }
    }

}