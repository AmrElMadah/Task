package com.example.myapplication.fabionaccinumbers.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class FibonacciViewModel : ViewModel() {
    val mFibonacciNumbersState: SnapshotStateList<BigInteger> = mutableStateListOf(BigInteger("0"), BigInteger("1"))

    init {
        produceFibonacciNumbers(2)
    }

    fun produceFibonacciNumbers(fibonacciIndex: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            // Null means no more fibonacci numbers
            getNextFibonacciNumberRepository(
                mFibonacciNumbersState[fibonacciIndex - 1],
                mFibonacciNumbersState[fibonacciIndex - 2]
            )?.let { fibonacciNumber ->
                mFibonacciNumbersState.add(fibonacciNumber)
                produceFibonacciNumbers(fibonacciIndex + 1)
            }
        }
    }

    // Android Studio does not understand that Thread.sleep() is executed in background
    @Suppress("BlockingMethodInNonBlockingContext")
    /**
     * Calculate next fibonacci number after [previousLastFibonacciNumber] and
     * [lastFibonacciNumber]
     *
     * @return next Fibonacci number or null if limit of ULong.MAX_VALUE is exceeded
     */
    private suspend fun getNextFibonacciNumberRepository(
        lastFibonacciNumber: BigInteger,
        previousLastFibonacciNumber: BigInteger
    ): BigInteger? = withContext(Dispatchers.IO) {
        Thread.sleep(500)
        val output = lastFibonacciNumber + previousLastFibonacciNumber
        if (output <= BigInteger(ULong.MAX_VALUE.toString())) {
            return@withContext output
        } else {
            return@withContext null
        }
    }
}
