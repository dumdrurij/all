package com.example.dogapi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.dogapi.databinding.ActivityMainBinding
import com.example.dogapi.network.NetworkClient
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkClient: NetworkClient = NetworkClient()
        networkClient.initClient()


        binding.newDog.setOnClickListener {
            Log.i("DOG APP DEBUG", " the button is pressed")

            networkClient.getDog { url ->
                Picasso.get().load(url).into(binding.dogImage)
            }

        }
    }
}