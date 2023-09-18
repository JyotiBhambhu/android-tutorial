package com.jyoti.androidtutorial.home.domain.repo

fun interface HomeRepo{
    fun getHomeData(response: (String) -> Unit, error: (String) -> Unit)
}