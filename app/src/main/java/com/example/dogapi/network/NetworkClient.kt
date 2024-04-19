package com.example.dogapi.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.dogapi.network.RandomDogAnswer as RandomDogAnswer



class NetworkClient {
    private var service: DogApiService? = null

    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    fun initClient() {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        service = retrofit.create(DogApiService::class.java)

    }


    fun getDog(callback: (String?) -> Unit) {

        service?.getRandomDog()?.enqueue(object : Callback<RandomDogAnswer> {
            override fun onResponse(call: Call<RandomDogAnswer>,
                                    response: retrofit2.Response<RandomDogAnswer>) {
                val dogImageUrl = response.body()?.message
                Log.i("DOG APP DEBUG", "the dog image address is $dogImageUrl")
                callback.invoke(dogImageUrl)
            }

             override fun onFailure(call: Call<RandomDogAnswer>, error: Throwable) {
                    Log.e(
                       "DOG APP DEBUG",
                        "It's an error while we were trying to get a dog image",
                        error
                    )

                }

            })



        }

        }







 //fun getPicturesList(callback: (List<String>?) -> Unit) {
  //  service?.getDogPicturesList()?.enqueue(object : Callback<DogPictures> {
      //  override fun onResponse(call: Call<DogPictures>, response: Response<DogPictures>) {
      //      val list = response.body()?.message
      //      callback.invoke(list)
      //  }

     //   override fun onFailure(call: Call<DogPictures>, error: Throwable) {
     //       Log.e("ApiClient", "Cannot get dog picture list", error)
     //   }

 //   })
//}
//}

