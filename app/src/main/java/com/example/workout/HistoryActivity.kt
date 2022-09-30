package com.example.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.workout.databinding.ActivityHistoryBinding
import com.example.workout.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

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
        val historyDao =(application as WorkoutApp).db?.historyDao()
        getAllDates(historyDao)

    }
    private fun getAllDates(historyDao: HistoryDao?){
        lifecycleScope.launch {
            historyDao?.fetchAllDates()?.collect(){
                allDatesList ->
                for(i in allDatesList){
                    Log.e("Date: ","" +i.date)
                }
            }
        }
    }


}