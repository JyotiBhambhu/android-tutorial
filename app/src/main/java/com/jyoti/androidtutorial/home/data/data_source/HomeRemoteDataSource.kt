package com.jyoti.androidtutorial.home.data.data_source

import com.jyoti.androidtutorial.home.data.model.HomeResponseModel
import retrofit2.Call
import retrofit2.http.GET


fun interface HomeRemoteDataSource {

    @GET("/home")
    fun fetchHomeDataRemote(): Call<HomeResponseModel?>

}