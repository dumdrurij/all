package com.example.dogapi.network

import retrofit2.Retrofit

class NetworkClient {
    private var service: DogApiService? = null

    fun initClient() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .build()

        val service = retrofit.create(DogApiService::class.java)

    }

    fun getDog() {
        service?.getRandomDog()
    }
}