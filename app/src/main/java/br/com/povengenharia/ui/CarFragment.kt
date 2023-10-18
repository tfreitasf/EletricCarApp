package br.com.povengenharia.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.R
import br.com.povengenharia.data.CarsApi
import br.com.povengenharia.domain.Car
import br.com.povengenharia.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CarFragment : Fragment() {
    lateinit var fabtnCalculate: FloatingActionButton
    lateinit var carList: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var noConnectionImage: ImageView
    lateinit var noConnectionText: TextView
    lateinit var carsApi: CarsApi



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.car_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRetrofit()
        setupView(view)
        setupListner()


    }

    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)) {
            getAllCars()
        } else {
            empetyState()


        }
    }

    fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tfreitasf.github.io/EletricCarApi/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        carsApi = retrofit.create(CarsApi::class.java)

    }

    fun getAllCars() {
        carsApi.getAllCars().enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                if (response.isSuccessful) {
                    progressBar.visibility = View.GONE
                    noConnectionImage.visibility = View.GONE
                    noConnectionText.visibility = View.GONE

                    response.body()?.let {
                        setupList(it)

                    }
                } else {
                    Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                Toast.makeText(context, R.string.response_error, Toast.LENGTH_LONG).show()
            }

        })
    }

    fun empetyState() {
        progressBar.visibility = View.GONE
        carList.visibility = View.GONE
        noConnectionImage.visibility = View.VISIBLE
        noConnectionText.visibility = View.VISIBLE
    }

    fun setupView(view: View) {
        view.apply {
            fabtnCalculate = findViewById(R.id.fab_calculate)
            carList = findViewById(R.id.rv_car_list)
            progressBar = findViewById(R.id.pb_loader)
            noConnectionImage = findViewById(R.id.iv_empty_state)
            noConnectionText = findViewById(R.id.tv_no_connection)
        }

    }

    fun setupList(lista: List<Car>) {
        val carsAdapter = CarAdapter(lista)
        carList.visibility = View.VISIBLE
        carList.adapter = carsAdapter
        carsAdapter.carItemLister = {carro ->
            val battery  = carro.battery


        }

    }

    fun setupListner() {
        fabtnCalculate.setOnClickListener {
            startActivity(Intent(context, CalculateAutonomyActivity::class.java))

        }
    }


    fun checkForInternet(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false

            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }

        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }


}