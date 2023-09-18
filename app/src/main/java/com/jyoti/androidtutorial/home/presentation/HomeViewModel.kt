package com.jyoti.androidtutorial.home.presentation

import androidx.lifecycle.ViewModel
import com.jyoti.androidtutorial.home.domain.HomeUseCase
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(val homeUseCase: HomeUseCase) : ViewModel() {

    fun getHomeData(response: (String) -> Unit, error: (String) -> Unit) {
        homeUseCase(response, error)
    }

}