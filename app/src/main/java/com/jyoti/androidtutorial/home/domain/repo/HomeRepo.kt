package com.jyoti.androidtutorial.home.domain.repo

interface HomeRepo{
    fun getHomeData(response: (String) -> Unit, error: (String) -> Unit)
}