package com.example.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.workout.databinding.ActivityHistoryBinding
import com.example.workout.databinding.ActivityMainBinding

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbarHistoryActivity)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "HISTORY"
        }
        binding.toolbarHistoryActivity.setNavigationOnClickListener{
            onBackPressed()
        }

    }
}