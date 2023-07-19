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
import com.jyoti.androidtutorial.home.data.data_source.HomeRemoteDataSource
import com.jyoti.androidtutorial.home.data.repo.HomeRepoImpl
import com.jyoti.androidtutorial.home.domain.HomeUseCase
import retrofit2.Retrofit


@Composable
fun HomeScreen(
    retrofit: Retrofit,
    homeViewModel: HomeViewModel = HomeViewModel(
        homeUseCase = HomeUseCase(
            homeRepo = HomeRepoImpl(
                homeRemoteDataSource = retrofit.create(HomeRemoteDataSource::class.java)
            )
        )
    )
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