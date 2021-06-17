package com.example.arioproject

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arioproject.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = linearLayoutManager

        val adapter = RecyclerAdapter()
        binding.recyclerView.setAdapter(adapter)

        APIHandler.notificationToActivity(this);
        startStoryFetchAsync()
    }

    private fun startStoryFetchAsync() = GlobalScope.async {
        APIHandler.fetchTopStory()
    }

    fun recieveUpdateNotificationFromAPI() {

        runOnUiThread {
            binding.recyclerView.adapter?.notifyDataSetChanged();
        }

    }



}