package com.example.myapplication.fabionaccinumbers.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.fabionaccinumbers.ui.viewmodel.FibonacciViewModel
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val fibonacciViewModel by viewModels<FibonacciViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    FibonacciNumbersList(fibonacciViewModel)
                }
            }
        }

        fibonacciViewModel.getNextFibonacciNumber()
    }
}

@Composable
fun FibonacciNumbersList(fibonacciViewModel: FibonacciViewModel) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(fibonacciViewModel.mFibonacciNumbersState) { fibonacciNumber ->
            Text(
                text = fibonacciNumber.toString(),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(10.dp)
            )
        }
    }
}