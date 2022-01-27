package com.example.myapplication.fabionaccinumbers.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.math.BigInteger

@Composable
fun FibonacciNumbersList(fibonacciNumbersState: SnapshotStateList<BigInteger>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(fibonacciNumbersState) { fibonacciNumber ->
            Text(
                text = fibonacciNumber.toString(),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp)
            )
        }
    }
}
