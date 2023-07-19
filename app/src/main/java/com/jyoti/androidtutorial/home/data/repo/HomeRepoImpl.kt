package com.jyoti.androidtutorial.home.data.repo

import com.jyoti.androidtutorial.home.data.data_source.HomeRemoteDataSource
import com.jyoti.androidtutorial.home.data.model.HomeResponseModel
import com.jyoti.androidtutorial.home.domain.repo.HomeRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeRepoImpl(private val homeRemoteDataSource: HomeRemoteDataSource) : HomeRepo {
    override fun getHomeData(response: (String) -> Unit, error: (String) -> Unit) {
        homeRemoteDataSource.fetchHomeDataRemote()?.enqueue(object : Callback<HomeResponseModel?> {

            override fun onResponse(
                call: Call<HomeResponseModel?>,
                response: Response<HomeResponseModel?>
            ) {
                response.body()?.data?.let {
                    response(it)
                } ?: error("Something Went Wrong")
            }

            override fun onFailure(call: Call<HomeResponseModel?>, t: Throwable) {
                error(t.message ?: "Something Went Wrong")
            }
        })
    }
}