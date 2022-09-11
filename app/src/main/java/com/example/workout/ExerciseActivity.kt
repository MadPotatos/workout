package com.example.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.workout.databinding.ActivityExerciseBinding
import com.example.workout.databinding.ActivityMainBinding

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarExercise)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbarExercise.setNavigationOnClickListener{
            onBackPressed()
        }
        setupRestView()
    }
    private fun setupRestView(){
        if(restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }
private fun setRestProgressBar(){
    binding.progressBar.progress = restProgress
    restTimer = object: CountDownTimer(10000,1000){
        override fun onTick(p0: Long) {
           restProgress++
            binding.progressBar.progress = 10 - restProgress
            binding.tvTimer.text = (10 - restProgress).toString()
        }

        override fun onFinish() {
           Toast.makeText(this@ExerciseActivity,"Start",Toast.LENGTH_SHORT).show()
        }

    }.start()
}

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }


    }


}