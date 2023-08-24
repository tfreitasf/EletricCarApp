package br.com.povengenharia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var price: EditText
    lateinit var travelledDistance: EditText
    lateinit var btnCalculate: Button
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        setupListners()

    }

    fun setupView() {
        price = findViewById(R.id.et_kwhprice)
        btnCalculate = findViewById(R.id.bt_calculate)
        travelledDistance = findViewById(R.id.et_travelleddistance)
        result = findViewById(R.id.tv_result)

    }

    fun setupListners() {

        btnCalculate.setOnClickListener {
            calculate()
        }
    }

    fun calculate() {
        val price = price.text.toString().toFloat()
        val travelledDistance = travelledDistance.text.toString().toFloat()

        val costKmTraveled = price / travelledDistance
        result.text = costKmTraveled.toString()

    }
}