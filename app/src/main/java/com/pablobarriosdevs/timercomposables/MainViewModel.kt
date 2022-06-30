package com.pablobarriosdevs.timercomposables

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.pablobarriosdevs.stopwatchorcountdown.formatter.Patterns
import com.pablobarriosdevs.stopwatchorcountdown.timers.StopWatch
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel(){

    val baseTime = MutableStateFlow(0L)
    var sw = StopWatch(0L, Patterns.MM_SS_SS)
}