package com.example.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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
                if(allDatesList.isNotEmpty()){
                    binding.rvHistory.layoutManager = LinearLayoutManager(this@HistoryActivity)

                    val dates = ArrayList<String>()
                    for(date in allDatesList){
                        dates.add(date.date)
                    }
                    val historyAdapter = HistoryAdapter(dates)

                    binding.rvHistory.adapter = historyAdapter
                }
            }
        }
    }


}