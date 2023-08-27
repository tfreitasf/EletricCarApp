package br.com.povengenharia.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.R
import br.com.povengenharia.data.CarFactory
import br.com.povengenharia.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CarFragment : Fragment() {
    lateinit var fabtnCalculate: FloatingActionButton
    lateinit var carList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()
        setupListner()
    }

    fun setupView(view : View){
        fabtnCalculate = view.findViewById(R.id.fab_calculate)
        carList = view.findViewById(R.id.rv_car_list)

    }

    fun setupList() {

        val adapter = CarAdapter(CarFactory.list)
        carList.adapter = adapter

    }

    fun setupListner(){
        fabtnCalculate.setOnClickListener {
            startActivity(Intent(context, CalculateAutonomyActivity::class.java))

        }
    }
}