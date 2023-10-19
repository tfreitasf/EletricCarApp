package br.com.povengenharia.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.R
import br.com.povengenharia.data.local.CarRepository
import br.com.povengenharia.domain.Car
import br.com.povengenharia.ui.adapter.CarAdapter

class FavoriteFragment : Fragment(){

    lateinit var favoriteCarList: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorite_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupList()




    }

    fun setupView(view: View) {
        view.apply {

            favoriteCarList = findViewById(R.id.rv_favorite_car_list)

        }

    }

    fun setupList() {
        val cars = getCarOnLocalDb()
        val carsAdapter = CarAdapter(cars)
        favoriteCarList.visibility = View.VISIBLE
        favoriteCarList.adapter = carsAdapter
        carsAdapter.carItemLister = { carro ->
            //val isSaved = CarRepository(requireContext()).saveIfNotExist(carro)

        }

    }

    fun getCarOnLocalDb(): List<Car>{
        val repository = CarRepository(requireContext())
        val carList =  repository.getAll()
        return carList

    }


}