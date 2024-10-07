package com.example.mytimerx.intervaltimer

import android.os.Handler
import android.os.Looper

class CountDownTimerWithIntervals {
    private var handler: Handler = Handler(Looper.getMainLooper())
    private var time: Long = 0 // This will track the current time, whether counting up or down
    private var interval: Long = 1000L // Default interval of 1 second
    private var isPaused = false
    private var startTime: Long = 0 // Time when the timer starts/resumes
    private var listener: ((Long) -> Unit)? = null
    private var finishListener: (() -> Unit)? = null
    private var format: TimeFormat = TimeFormat.SECONDS // Default format is seconds
    private var counterUp: Boolean = false // Indicates whether to count up or down

    // Runnable to run the timer task
    private val timerRunnable = object : Runnable {
        override fun run() {
            val currentTime = System.currentTimeMillis()
            val elapsedTime = currentTime - startTime
            startTime = currentTime

            if (counterUp) {
                time += elapsedTime
            } else {
                time -= elapsedTime
                if (time <= 0) {
                    time = 0
                    stop() // Stop the timer and prevent further posts
                    return // Exit early to avoid further handler.postDelayed calls
                }
            }

            // Convert the time into the specified format before invoking the listener
            val timeToDisplay = when (format) {
                TimeFormat.SECONDS -> time / 1000
                TimeFormat.MINUTES -> time / (1000 * 60)
                TimeFormat.HOURS -> time / (1000 * 60 * 60)
                TimeFormat.MILLISECONDS -> time
            }

            // Invoke the listener with the time to display
            listener?.invoke(timeToDisplay)
            handler.postDelayed(this, interval) // Schedule the next tick only if the timer is still running
        }
    }

    // Enum for different time formats
    enum class TimeFormat {
        SECONDS, MINUTES, HOURS, MILLISECONDS
    }

    fun start(milliseconds: Long,interval: Long = 1000L, format: TimeFormat,
              counterUp: Boolean, onTick: (Long) -> Unit, onFinish: () -> Unit) {
        listener = onTick
        finishListener = onFinish
        this.format = format // Set the format provided by the user
        this.counterUp = counterUp // Set the counter direction
        this.interval *= interval // Set the interval provided by the user

        // Initialize the time variable
        time = if (counterUp) 0 else when (format) {
            TimeFormat.SECONDS -> milliseconds * 1000
            TimeFormat.MINUTES -> milliseconds * 1000 * 60
            TimeFormat.HOURS -> milliseconds * 1000 * 60 * 60
            TimeFormat.MILLISECONDS -> milliseconds
        }

        isPaused = false
        startTime = System.currentTimeMillis() // Set start time

        handler.removeCallbacks(timerRunnable) // Clear previous runnable
        handler.post(timerRunnable) // Start the timer
    }

    fun pause() {
        if (!isPaused) {
            isPaused = true
            handler.removeCallbacks(timerRunnable) // Stop further callbacks
        }
    }

    fun resume() {
        if (isPaused) {
            isPaused = false
            startTime = System.currentTimeMillis() // Reset start time for the next tick
            handler.post(timerRunnable) // Resume the timer
        }
    }

    fun stop() {
        handler.removeCallbacks(timerRunnable) // Cancel the timer
        finishListener?.invoke()
        time = 0 // Reset the time
        isPaused = false
    }

    fun reset() {
        stop() // Stop and reset the timer
        time = 0
        isPaused = false
    }
}