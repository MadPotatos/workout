package com.example.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import com.example.workout.databinding.ActivityBmiBinding
import com.example.workout.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNIT_VIEW"
    }
    private lateinit var binding: ActivityBmiBinding
    private var currentVisibleView: String = "METRIC_UNIT_VIEW"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbarBmiActivity)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding.toolbarBmiActivity.setNavigationOnClickListener{
           onBackPressed()
        }
        makeVisibleMetricUnitsView()
        binding.rgUnit.setOnCheckedChangeListener{_,checkedId: Int ->
            if(checkedId == R.id.rbtnMetricUnit){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUSUnitsView()
            }
        }
        binding.btnCalculate.setOnClickListener {
            calculateUnits()
        }
    }
    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW
        binding.tilHeight.visibility = View.VISIBLE
        binding.tilWeight.visibility = View.VISIBLE
        binding.tilUSWeight.visibility = View.GONE
        binding.tilUSHeightFeet.visibility = View.GONE
        binding.tilUSHeightInch.visibility = View.GONE

        binding.etHeight.text!!.clear()
        binding.etWeight.text!!.clear()
        binding.llDisplayResult.visibility = View.INVISIBLE
    }
    private fun makeVisibleUSUnitsView(){
        currentVisibleView = US_UNITS_VIEW
        binding.tilHeight.visibility = View.INVISIBLE
        binding.tilWeight.visibility = View.INVISIBLE
        binding.tilUSWeight.visibility = View.VISIBLE
        binding.tilUSHeightFeet.visibility = View.VISIBLE
        binding.tilUSHeightInch.visibility = View.VISIBLE

        binding.etUSHeightFeet.text!!.clear()
        binding.etUSHeightInch.text!!.clear()
        binding.etUSWeight.text!!.clear()
        binding.llDisplayResult.visibility = View.INVISIBLE
    }
    private fun displayBMIResult(bmi: Float){
        val bmiLabel : String
        val bmiDescription : String
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        if(bmi.compareTo(15f) <=0){
            bmiLabel = "Very severely underweight"
            bmiDescription = "You really need to take better care of yourself! Eat more calories please!"
        }
        else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0 ){
            bmiLabel = "Severely underweight"
            bmiDescription = "You really need to take better care of yourself! Eat more calories please!"
        }
        else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0 ){
            bmiLabel = "Underweight"
            bmiDescription = "You really need to take better care of yourself! Eat more calories please!"
        }
        else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0 ){
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        }
        else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0 ){
            bmiLabel = "Overweight"
            bmiDescription = "You really need to take better care of yourself! You lack of exercise and need to workout"
        }
        else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0 ){
            bmiLabel = "Moderately obese"
            bmiDescription = "You really need to take better care of yourself! You lack of exercise and need to workout"
        }
        else if(bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0 ){
            bmiLabel = "Severely obese"
            bmiDescription = "You need to workout now !! You are in a very dangerous condition!"
        }
        else{
            bmiLabel = "Very severely obese"
            bmiDescription = "You need to workout now !! You are in a very dangerous condition!"
        }
        binding.llDisplayResult.visibility = View.VISIBLE
        binding.tvBMIValue.text = bmiValue
        binding.tvBMIType.text = bmiLabel
        binding.tvBMIDescription.text = bmiDescription
    }
    private fun validateMetricUnits():Boolean{
        var isValid = true
        if(binding.etWeight.text.toString().isEmpty()){
            isValid = false
        }else if (binding.etHeight.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }
    private fun validateUsUnits():Boolean{
        var isValid = true
        if(binding.etUSHeightFeet.text.toString().isEmpty()){
            isValid = false
        }else if (binding.etUSHeightInch.text.toString().isEmpty()){
            isValid = false
        }else if (binding.etUSWeight.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }
    private fun calculateUnits(){
        if(currentVisibleView == METRIC_UNITS_VIEW){
            if(validateMetricUnits()){
                val heightValue : Float = binding.etHeight.text.toString().toFloat()/100
                val weightValue : Float = binding.etWeight.text.toString().toFloat()
                val bmi = weightValue / (heightValue*heightValue)
                displayBMIResult(bmi)
            }else{
                Toast.makeText(this,"Please enter valid values.",Toast.LENGTH_SHORT).show()
            }

        }else{
            if(validateUsUnits()){
                val usHeightValueFeet: String = binding.etUSHeightFeet.text.toString()
                val usHeightValueInch: String = binding.etUSHeightInch.text.toString()
                val usWeightValue : Float = binding.etUSWeight.text.toString().toFloat()
                val usHeightValue = usHeightValueInch.toFloat() +  usHeightValueFeet.toFloat() * 12
                val bmi = 703 *(usWeightValue/(usHeightValue*usHeightValue))
                displayBMIResult(bmi)
            } else{
            Toast.makeText(this,"Please enter valid values.",Toast.LENGTH_SHORT).show()
        }
        }
    }

}