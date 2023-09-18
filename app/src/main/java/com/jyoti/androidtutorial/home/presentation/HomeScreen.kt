package com.jyoti.androidtutorial.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel
) {
    val isLoading = remember {
        mutableStateOf(true)
    }
    val data = remember {
        mutableStateOf("")
    }
    val error = remember {
        mutableStateOf("")
    }
    homeViewModel.getHomeData(response = {
        data.value = it
        isLoading.value = false
    }, error = {
        error.value = it
        isLoading.value = false
    })
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        if (isLoading.value) {
            HomeScreenLoadingView()
        }
        if (data.value.isNotEmpty()) {
            HomeScreenContentView(data = data.value)
        }
        if (error.value.isNotEmpty()) {
            HomeScreenErrorView(error = error.value)
        }
    }

}

@Composable
fun HomeScreenContentView(data: String) {
    Text(text = "Hello $data")
}

@Composable
fun HomeScreenErrorView(error: String) {
    Text(text = error)
}

@Composable
fun HomeScreenLoadingView() {
    CircularProgressIndicator()
}