package com.example.workout

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workout.databinding.ActivityExerciseBinding
import java.util.*
import kotlin.collections.ArrayList


class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityExerciseBinding
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseAdapter : ExerciseStatusAdapter? = null
    private var exerciseProgress = 0
    private lateinit var tts: TextToSpeech
    private var player: MediaPlayer? = null
    private lateinit var exerciseList: ArrayList<ExerciseModel>
    private var currentExercisePosition = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbarExercise)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        exerciseList = Constants.defaultExerciseList()
        tts = TextToSpeech(this,this)
        binding.toolbarExercise.setNavigationOnClickListener{
            onBackPressed()
        }
        setupRestView()
        setupExerciseStatusRecycleView()
    }

    private fun setupExerciseStatusRecycleView() {
        binding.rvExerciseStatus.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter = ExerciseStatusAdapter(exerciseList)
        binding.rvExerciseStatus.adapter = exerciseAdapter
    }

    private fun setupRestView(){

        try{
            val soundURI = Uri.parse("android.resource://com.example.workout/" +R.raw.press_start)
            player = MediaPlayer.create(applicationContext,soundURI)
            player?.isLooping = false
            player?.start()
        }catch(e: Exception){
            e.printStackTrace()
        }


        binding.flRestView.visibility = View.VISIBLE
        binding.tvTitle.visibility = View.VISIBLE
        binding.tvExerciseName.visibility = View.INVISIBLE
        binding.flExerciseBar.visibility = View.INVISIBLE
        binding.ivImage.visibility = View.INVISIBLE
        binding.tvUpcomingExerciseName.visibility = View.VISIBLE
        binding.tvUpcomingLabel.visibility = View.VISIBLE
        if(restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        binding.tvUpcomingExerciseName.text = exerciseList[currentExercisePosition+1].getName()
        setRestProgressBar()
    }

    private fun setupExerciseView(){
        binding.flRestView.visibility = View.INVISIBLE
        binding.tvTitle.visibility = View.INVISIBLE
        binding.tvExerciseName.visibility = View.VISIBLE
        binding.flExerciseBar.visibility = View.VISIBLE
        binding.ivImage.visibility = View.VISIBLE
        binding.tvUpcomingExerciseName.visibility = View.INVISIBLE
        binding.tvUpcomingLabel.visibility = View.INVISIBLE
        if(exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList[currentExercisePosition].getName())
        binding.ivImage.setImageResource(exerciseList[currentExercisePosition].getImage())
        binding.tvExerciseName.text = exerciseList[currentExercisePosition].getName()
        setExerciseProgressBar()
    }
private fun setRestProgressBar(){
    binding.restBar.progress = restProgress
    restTimer = object: CountDownTimer(10000,1000){
        override fun onTick(p0: Long) {
           restProgress++
            binding.restBar.progress = 10 - restProgress
            binding.tvTimer.text = (10 - restProgress).toString()
        }

        override fun onFinish() {
            currentExercisePosition++
            exerciseList[currentExercisePosition].setIsSelected(true)

            setupExerciseView()
        }

    }.start()
}

    private fun setExerciseProgressBar(){
        binding.progressBarExercise.progress = exerciseProgress
        exerciseTimer = object: CountDownTimer(30000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding.progressBarExercise.progress = 30 - exerciseProgress
                binding.tvTimerExercise.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if(currentExercisePosition < exerciseList.size -1 ){
                    setupRestView()
                }else{
                    Toast.makeText(this@ExerciseActivity,"Congratulation ! You complete the workout",Toast.LENGTH_SHORT).show()
                }
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        if(exerciseTimer != null) {
            exerciseTimer?.cancel()
            exerciseProgress = 0
        }
        tts.stop()
        tts.shutdown()
        if(player != null){
            player!!.stop()
        }
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result =tts.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","The Language specified is not supported!")
            }
        } else{
            Log.e("TTS","Initialization Failed!")
        }
    }
    private fun speakOut(text: String){
        tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }


}