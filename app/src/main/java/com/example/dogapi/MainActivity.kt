package com.example.dogapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dogapi.databinding.ActivityMainBinding
import com.example.dogapi.network.NetworkClient

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkClient: NetworkClient = NetworkClient()
        networkClient.initClient()
        networkClient.getDog()

        binding.newDog.setOnClickListener { networkClient.getDog()
        }
    }
}