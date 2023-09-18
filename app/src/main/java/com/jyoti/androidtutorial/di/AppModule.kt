package com.jyoti.androidtutorial.di

import com.jyoti.androidtutorial.home.data.data_source.HomeRemoteDataSource
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import org.koin.ksp.generated.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun appModule() = listOf(AppModule().module)

@Module
@ComponentScan("com.jyoti.androidtutorial")
class AppModule{

    @Single
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://5c3cad68-1819-4e97-bdf3-6cc57a521e18.mock.pstmn.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Factory
    fun providesHomeApi(retrofit: Retrofit): HomeRemoteDataSource {
        return retrofit.create(HomeRemoteDataSource::class.java)
    }
}