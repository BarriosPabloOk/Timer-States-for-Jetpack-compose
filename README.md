# Timer States For Jetpack Compose
A very useful tool to implement in your Android projects.
It provides a remember function that you can apply to your composables to start, pause, and stop a timer, in which you can specify a start time, a format pattern, and whether it's a stop watch or a countdown.

This library is able to get the default formatted time with the help of a compose state.

A StopWatchOrCountdown class is also provided that you can instantiate in a ViewModel.

## Current Last Version
- 2.0.0

## How to install dependencies
1. Add it in your root build.gradle at the end of repositories and settings.gradle:
```groovy
//at build.gradle in project
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```groovy
// at settings.gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven{url 'https://jitpack.io'}
    }
}
```
2. Add the dependency at build.gradle
```groovy
//build.gradle in app module
dependencies {
    implementation 'com.github.BarriosPabloOk:Timer-States-for-Jetpack-compose:<version>'
}
```

## How to use this library
1. After installing the necessary dependencies, create  a ***remember state function***  in your project.
   To set an initial time, you must assign a value in milliseconds in the*** time*** parameter.
   You can also change the appearance of the formatting by changing the value of ***pattern***.
   To work exactly, you can use the ***rememerStopWatch() ***and ***rememberCountdown()*** functions. The latter has a built-in lambda to be executed when the countdown reaches its end.
   If you are not interested in working exactly, you can use ***rememberStopWatchOrCountdown()***, in which you must indicate in the ***countdown*** parameter whether it will behave as Stopwatch or countdown.

   Likewise, you also have the* **StopWatch***, ***CountDown*** and* **StopWatchOrCountdown*** classes to be instantiated from a viewmodel.

```java
// Declare within Jetpack Compose's setContent function or within another composable function
// The sw state will behave like a Stop watch that will start with 5 seconds of initial time. 
//The pattern was left to format the time that came by default. It will look like this: "mm:ss.SS"

setContent {

   val sw = rememberStopWatch(time = 5000, pattern = Patterns.HH_MM_SS_SS)

	val cd = rememberCountdown()
	{
	 //runs at the end of the countdown
	Toast.makeText(context, "final", Toast.LENGTH_LONG).show() 
	}
	
	 val sw2 = rememberStopWatchOrCountdownState(time = 5000, countDown = false)

}
```
2. To display the elapsed time, you can use a Text() function and define in the parameter ***text = sw.timeFormatted.value***
```java
Text(
    text = sw.timeFormatted.collectAsState().value 
)
```
3. To start, pause, and stop the timer you are coding, *rememberStopWatchOrCountDownState()* returns an instance of a ***StopWatchOrCountdown*** object, so you can use its states.
   Here's how to implement one of those methods from a button.
```java
Button(
    onClick = { sw.start( ) }
) {
    Text(text = "START")
}
```

## StopWatch ( millis:Long, private val pattern:Patterns): TimeActions
### Public Properties

|Name|Type|Visibility|
| ------------ | ------------ | ------------ |
|var timeInMillis| Long|Read only (private set)|
|val timeFormatter| MutableStateFlow < String > |Read only (private set)|

### Public Method

|  Name |Return Type  |Description|
| ------------ | ------------ | ------------ |
|  start() |Unit   |start the timer  |
| pause() |Unit  | pause the timer  |
|reset()  | Unit  | reset the timer  |

## CountDown ( millis:Long, private val pattern:Patterns, private val finish:()->Unit): TimeActions
### Public Properties

|Name|Type|Visibility|
| ------------ | ------------ | ------------ |
|var timeInMillis| Long|Read only (private set)|
|val timeFormatter| MutableStateFlow < String > |Read only (private set)|

### Public Method

|  Name |Return Type  |Description|
| ------------ | ------------ | ------------ |
|  start() |Unit   |start the timer  |
| pause() |Unit  | pause the timer  |
|reset()  | Unit  | reset the timer  |

## StopWatchOrCountdown ( _millis:Long, private val timePattern:Patterns, countdown:Boolean)
### Public Properties

|Name|Type|Visibility|
| ------------ | ------------ | ------------ |
|isRunning |Boolean|Read only (private set)|
|timeInMillis| Long|Read only (private set)|
|timeFormatter| MutableStateFlow < String >? |Read only (private set)|

### Public Method

|  Name |Return Type  |Description|
| ------------ | ------------ | ------------ |
|  start() |Unit   |start the timer  |
| pause() |Unit  | pause the timer  |
|reset()  | Unit  | reset the timer  |

## Formatter class
You can use this class to format the time values of type Long to String, and thus display them on the screen.
It also has static methods to format hours, minutes, seconds, and remaining milliseconds separately.

### Formatter methods

|  Name |Return Type  |Description|
| ------------ | ------------ | ------------ |
|formatTime(time : Long, pattern : Formatter)  | String  |returns a formatted string to display on the screen |

### Formatter.Companion methods

|  Name |Return Type  |Description|
| ------------ | ------------ | ------------ |
|  Long.hours() |Long   |returns the number of hours  |
| Long.minutes() |Long  | returns the number of minutes  |
|Long.seconds() |Long  | returns the number of seconds  |
|Long.millis()  | Long |returns the remaining number of milliseconds  |
|formatTime(time : Long, pattern : Formatter)  | String  |returns a formatted string to display on the screen |

## Patterns Enum
This class lists the various types of patterns available for supported formatting.

|Name|View|
| ------------ | ------------ |
|  MM_SS |00:00 |
| MM_SS_SS  |00:00.00 |
| HH_MM_SS  | 00:00:00 |
|HH_MM_SS_SS|00:00:00.00|

## Collaborate
This is a personal project that I did because it was difficult for me to implement this functionality to my applications.
If you have an idea to improve performance, or to make usability easier, feel free to make a pull request to this project. I will gladly analyze it.

##  Changelog
- 2.0.0
    - Formatter.formatTime() switch to Formatter.Companion.formatTime()
    - TimerActions interface created.
    - rememberCountdown() and rememberStopWatch() created. More accuracy.
    - CountDown and StopWatch classes created.
    - Patterns enum created
- 1.1.1
    - timeFormatted property's bug fixed
- 1.0.1
    - Locale.getDefault() added in Formatter class at formatTime() method
    - timeFormatted property type in StopWatchOrCountdown class  changed to MutableStateFlow<String>?
- 1.0
    - Initial release

## Demo Android app
Coming soon...

## License

**GNU Affero General Public License v3.0**

Permissions of this strongest copyleft license are conditioned on making available complete source code of licensed works and modifications, which include larger works using a licensed work, under the same license. Copyright and license notices must be preserved. Contributors provide an express grant of patent rights. When a modified version is used to provide a service over a network, the complete source code of the modified version must be made available.

Copyright (C) 2022  Pablo Barrios

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
