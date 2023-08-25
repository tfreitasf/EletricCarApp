package br.com.povengenharia.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.povengenharia.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {


    lateinit var fabtnCalculate: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        fabtnCalculate = findViewById(R.id.fab_calculate)
        fabtnCalculate.setOnClickListener {
            startActivity(Intent(this, CalculateAutonomyActivity::class.java))

        }
    }
}


