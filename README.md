# Timer X


![Library Logo](assets/timerxlogo.PNG)

## Overview

The **Timer X** library provides a collection of powerful and easy-to-use classes for managing time and performing various time-related operations such as countdown timers, formatting timestamps, and working with time zones. Whether you're building timers, formatting dates, or converting time between time zones, this library offers a simple and efficient solution for your time management needs.

## Demo

Here is a gif video that showcases the usage of this library in an Android project. The video covers how to implement timers, format time, and manage time zones effectively.

![Demo](assets/demo_timer_x.mp4)

## Installation

You can integrate this library into your Android project using Gradle:

dependencies {
implementation 'com.example:mytimerx:1.0.0'
}

## Features

- **Simple Countdown Timer**:
  A basic countdown timer that supports pause, resume, and reset functionalities.

- **CountDown Timer with Intervals**:
  A countdown timer that counts up or down in intervals, customizable with different formats.

- **Format Manager**:
  Provides utilities to format time from milliseconds or `LocalDateTime`, convert time between different formats, and perform operations like calculating time differences and validating time formats.

- **Time Zone Conversion**:
  Convert time between different time zones seamlessly.
- **Support for Multiple Time Formats**: Supports 12-hour and 24-hour time formats, along with customizable date and time formats.



## Getting Started

### **1. Simple Countdown Timer**
```
// Usage in an Activity or Fragment
val timer = SimpleCountDownTimer()

timer.start(60, SimpleCountDownTimer.TimeFormat.SECONDS, { remainingTime ->
    // Update your UI with remaining time
    Log.d("TIMER", "Remaining Time: $remainingTime seconds")
}, {
    // Timer finished
    Log.d("TIMER", "Timer Finished")
})

// To pause the timer
timer.pause()

// To resume the timer
timer.resume()

// To stop the timer
timer.stop()

// To reset the timer
timer.reset()
```

### **2. CountDown Timer with Intervals**
```
// Usage in an Activity or Fragment
val intervalTimer = CountDownTimerWithIntervals()

intervalTimer.start(10, 1, CountDownTimerWithIntervals.TimeFormat.SECONDS, false, { remainingTime ->
    // Update your UI with remaining time
    Log.d("INTERVAL_TIMER", "Remaining Time: $remainingTime seconds")
}, {
    // Timer finished
    Log.d("INTERVAL_TIMER", "Timer Finished")
})
```
### **3. Format Manager (Time Formatting and Parsing)**
```

// Usage in an Activity or Fragment
val formatManager = FormatManager()

// Format current time
val formattedTime = formatManager.formatTime(timeInMillis = System.currentTimeMillis(), format = "dd/MM/yyyy HH:mm")
Log.d("TIME_FORMAT", "Formatted Time: $formattedTime")

// Convert milliseconds to formatted string
val formattedMillis = formatManager.convertMillisToFormattedString(timeInMillis = 10000000, format = "HH:mm:ss")
Log.d("TIME_FORMAT", "Converted Time: $formattedMillis")
```

# Documentation

### 1. SimpleCountDownTimer Class
```
- **start(milliseconds: Long, format: TimeFormat, onTick: (Long) -> Unit, onFinish: () -> Unit)**: Starts the countdown timer with a specified duration.
- **pause()**: Pauses the timer.
- **resume()**: Resumes the paused timer.
- **stop()**: Stops the timer.
- **reset()**: Resets the timer.
```
### 2. CountDownTimerWithIntervals Class
```
- **start(milliseconds: Long, interval: Long, format: TimeFormat, counterUp: Boolean, onTick: (Long) -> Unit, onFinish: () -> Unit)**: Starts a countdown timer that can count up or down at specified intervals.
- **pause()**: Pauses the timer.
- **resume()**: Resumes the timer.
- **stop()**: Stops the timer.
- **reset()**: Resets the timer.
```
### 3. FormatManager Class
```
- **formatTime(timeInMillis: Long?, dateTime: Date?, format: String, locale: Locale = Locale.getDefault())**: Formats time from either milliseconds or `LocalDateTime`.
- **convertMillisToFormattedString(timeInMillis: Long, format: String, locale: Locale = Locale.getDefault())**: Converts milliseconds into a formatted string.
- **getCurrentTime24HourFormat()**: Returns the current time in a 24-hour format.
- **getCurrentTime12HourFormat()**: Returns the current time in a 12-hour format.
- **timeDifference(time1: String, time2: String)**: Calculates the time difference between two times in `HH:mm` format.
- **isValidTimeFormat(time: String)**: Validates whether the provided time string matches the `HH:mm` format.

```

## Contributing


We welcome contributions to the TimerX project! Please review the [CONTRIBUTING](CONTRIBUTING.md) guide for instructions on how to get involved.



## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.