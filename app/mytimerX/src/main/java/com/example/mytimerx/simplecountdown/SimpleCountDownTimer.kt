package com.example.mytimerx.simplecountdown

import android.os.Handler
import android.os.Looper

class SimpleCountDownTimer {
    private var handler: Handler = Handler(Looper.getMainLooper())
    private var remainingTime: Long = 0
    private var interval: Long = 1000L // Default interval of 1 second
    private var isPaused = false
    private var startTime: Long = 0 // Time when the timer starts/resumes
    private var timeLeftWhenPaused: Long = 0 // Time left when the timer is paused
    private var listener: ((Long) -> Unit)? = null
    private var finishListener: (() -> Unit)? = null
    private var format: TimeFormat = TimeFormat.SECONDS // Default format is seconds

    // Runnable to run the timer task
    private val timerRunnable = object : Runnable {
        override fun run() {
            if (remainingTime <= 0) {
                finishListener?.invoke() // Call finish listener
                stop() // Stop the timer
            } else {
                // Calculate the elapsed time since the last tick
                val currentTime = System.currentTimeMillis()
                remainingTime -= (currentTime - startTime) // Subtract the time since the last tick
                startTime = currentTime // Reset start time

                // Convert the remaining time into the specified format before invoking the listener
                val timeToDisplay = when (format) {
                    TimeFormat.SECONDS -> remainingTime / 1000
                    TimeFormat.MINUTES -> remainingTime / (1000 * 60)
                    TimeFormat.HOURS -> remainingTime / (1000 * 60 * 60)
                    TimeFormat.MILLISECONDS -> remainingTime
                }

                listener?.invoke(timeToDisplay) // Update the listener with remaining time in the right format
                handler.postDelayed(this, interval) // Schedule the next tick
            }
        }
    }

    // Enum for different time formats
    enum class TimeFormat {
        SECONDS, MINUTES, HOURS, MILLISECONDS
    }

    fun start(milliseconds: Long, format: TimeFormat, onTick: (Long) -> Unit, onFinish: () -> Unit) {
        listener = onTick
        finishListener = onFinish
        this.format = format // Set the format provided by the user

        // Convert the input time into milliseconds based on the format
        remainingTime = when (format) {
            TimeFormat.SECONDS -> milliseconds * 1000
            TimeFormat.MINUTES -> milliseconds * 1000 * 60
            TimeFormat.HOURS -> milliseconds * 1000 * 60 * 60
            TimeFormat.MILLISECONDS -> milliseconds
        }

        isPaused = false
        timeLeftWhenPaused = remainingTime
        startTime = System.currentTimeMillis()

        handler.removeCallbacks(timerRunnable) // Clear previous runnables
        handler.post(timerRunnable) // Start the timer
    }

    fun pause() {
        if (!isPaused) {
            isPaused = true
            handler.removeCallbacks(timerRunnable) // Stop further callbacks
            timeLeftWhenPaused = remainingTime - (System.currentTimeMillis() - startTime) // Update remaining time
        }
    }

    fun resume() {
        if (isPaused) {
            isPaused = false
            remainingTime = timeLeftWhenPaused // Restore remaining time
            startTime = System.currentTimeMillis() // Reset start time for the next tick
            handler.post(timerRunnable) // Resume the timer
        }
    }

    fun stop() {
        handler.removeCallbacks(timerRunnable) // Cancel the timer
        remainingTime = 0 // Reset the remaining time
        isPaused = false
    }

    fun reset() {
        stop() // Stop and reset the timer
        remainingTime = 0
        isPaused = false
        timeLeftWhenPaused = 0
    }
}

