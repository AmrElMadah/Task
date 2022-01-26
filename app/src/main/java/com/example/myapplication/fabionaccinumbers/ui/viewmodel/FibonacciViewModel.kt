package com.example.myapplication.fabionaccinumbers.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class FibonacciViewModel : ViewModel() {
    val mFibonacciNumbersState = mutableStateListOf(BigInteger("0"), BigInteger("1"))
    private var nextFibonacciIndex = 2

    fun getNextFibonacciNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            delay(100)
            getNextFibonacciNumberRepository(
                mFibonacciNumbersState[nextFibonacciIndex - 1],
                mFibonacciNumbersState[nextFibonacciIndex - 2]
            ).collect {
                it.let { fibonacciNumber ->
                    if (fibonacciNumber > BigInteger.ZERO) {
                        withContext(Dispatchers.Main) {
                            mFibonacciNumbersState.add(fibonacciNumber)
                            nextFibonacciIndex++
                            getNextFibonacciNumber()
                        }
                    }
                }
            }
        }
    }

    private suspend fun getNextFibonacciNumberRepository(
        lastFibonacciNumber: BigInteger,
        previousLastFibonacciNumber: BigInteger
    ): Flow<BigInteger> =
        flow {
            if (lastFibonacciNumber <= BigInteger(ULong.MAX_VALUE.toString())) {
                val output = calculateNextFibonacciNumber(
                    lastFibonacciNumber,
                    previousLastFibonacciNumber
                )
                emit(
                    output
                )
            } else {
                emit(BigInteger.ZERO)
            }
        }

    private fun calculateNextFibonacciNumber(
        lastFibonacciNumber: BigInteger,
        previousLastFibonacciNumber: BigInteger
    ): BigInteger = lastFibonacciNumber + previousLastFibonacciNumber
}