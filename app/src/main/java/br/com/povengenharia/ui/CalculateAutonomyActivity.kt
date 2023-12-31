package br.com.povengenharia.ui

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.R

class CalculateAutonomyActivity : AppCompatActivity() {

    lateinit var price: EditText
    lateinit var travelledDistance: EditText
    lateinit var btnCalculate: Button
    lateinit var result: TextView
    lateinit var btnClose : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_autonomy)

        setupView()
        setupListners()
        setupCacheResult()


    }

    private fun setupCacheResult() {
        val calculatedValue = getSharedPref()
        result.text = calculatedValue.toString()
    }

    fun setupView() {
        price = findViewById(R.id.et_kwhprice)
        btnCalculate = findViewById(R.id.bt_calculate)
        travelledDistance = findViewById(R.id.et_travelleddistance)
        result = findViewById(R.id.tv_result)
        btnClose = findViewById(R.id.iv_closebutton)


    }

    fun setupListners() {

        btnCalculate.setOnClickListener {
            calculate()
        }

        btnClose.setOnClickListener{
            finish()
        }

    }

    fun calculate() {
        val price = price.text.toString().toFloat()
        val travelledDistance = travelledDistance.text.toString().toFloat()

        val costKmTraveled = price / travelledDistance
        result.text = costKmTraveled.toString()
        saveSharedPref(costKmTraveled)

    }

    fun saveSharedPref(result: Float){
        val sharedPref = getSharedPreferences("db", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()){
            putFloat(getString(R.string.saved_calc), result)
            apply()
        }
    }

    fun getSharedPref(): Float {
        val sharedPref = getSharedPreferences("db", Context.MODE_PRIVATE)
        return sharedPref.getFloat(getString(R.string.saved_calc), 0.0f)
    }




}


