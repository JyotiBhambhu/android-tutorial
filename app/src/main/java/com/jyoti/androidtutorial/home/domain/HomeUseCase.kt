package com.jyoti.androidtutorial.home.domain

import com.jyoti.androidtutorial.home.domain.repo.HomeRepo
import org.koin.core.annotation.Factory

@Factory
class HomeUseCase(private val homeRepo: HomeRepo) {

    operator fun invoke(response: (String) -> Unit, error: (String) -> Unit) {
        homeRepo.getHomeData(response, error)
    }

}