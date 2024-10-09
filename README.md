# TimerX

The **TimerX** library provides a collection of powerful and easy-to-use classes for managing time and performing various time-related operations such as countdown timers, formatting timestamps, and working with time zones. Whether you're building timers, formatting dates, or converting time between time zones, this library offers a simple and efficient solution for your time management needs.

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

## Step-by-Step Usage:

### 1. Dependency Addition

To use the TimerX, follow these steps to update your Gradle files.

#### Gradle Integration

##### Step A: Add Maven Repository
In your **project-level** `build.gradle` or `settings.gradle` file, add the following repository:

```
repositories {
    google()
    mavenCentral()
    maven { url "https://jitpack.io" }
}
```

### Step B: Add Dependencies

Include the TimerX library in your **app-level** `build.gradle` file. Replace `x.x.x` with the latest version:

```
implementation 'com.github.hypersoftdev:TimerX:x.x.x'
```

## Java/Kotlin Implementation

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
## Demo

Here is a gif video that showcases the usage of this library in an Android project. The video covers how to implement timers, format time, and manage time zones effectively.

https://github.com/user-attachments/assets/c21fcc3f-40cc-4a1e-a767-960e6065d14c


# Acknowledgements

This work would not have been possible without the invaluable contributions of [Wajahat Ahmed](https://github.com/AghaTech883). His expertise, dedication, and unwavering support have been instrumental in bringing this project to fruition.

![Profile](https://github.com/hypersoftdev/TimerX/blob/master/screen/profile_image.jpg?raw=true)

We are deeply grateful for [Wajahat Ahmed](https://github.com/AghaTech883) involvement and his belief in the importance of this work. His contributions have made a significant impact, and we are honored to have had the opportunity to collaborate with him.

# LICENSE

Copyright 2023 Hypersoft Inc

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
