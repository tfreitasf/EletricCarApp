package br.com.povengenharia.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.R
import br.com.povengenharia.domain.Car
import br.com.povengenharia.ui.adapter.CarAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL

class CarFragment : Fragment() {
    lateinit var fabtnCalculate: FloatingActionButton
    lateinit var carList: RecyclerView
    lateinit var progrssBar: ProgressBar
    lateinit var noConnectionImage: ImageView
    lateinit var noConnectionText: TextView

    var carsArray: ArrayList<Car> = ArrayList()

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
        setupListner()


    }

    override fun onResume() {
        super.onResume()
        if (checkForInternet(context)) {
            callService()
        } else {
            empetyState()


        }
    }

    fun empetyState(){
        progrssBar.visibility = View.GONE
        carList.visibility = View.GONE
        noConnectionImage.visibility = View.VISIBLE
        noConnectionText.visibility = View.VISIBLE
    }

    fun setupView(view: View) {
        view.apply {
            fabtnCalculate = findViewById(R.id.fab_calculate)
            carList = findViewById(R.id.rv_car_list)
            progrssBar = findViewById(R.id.pb_loader)
            noConnectionImage = findViewById(R.id.iv_empty_state)
            noConnectionText = findViewById(R.id.tv_no_connection)
        }

    }

    fun setupList() {
        val adapter = CarAdapter(carsArray)
        carList.visibility = View.VISIBLE
        carList.adapter = adapter

    }

    fun setupListner() {
        fabtnCalculate.setOnClickListener {
            startActivity(Intent(context, CalculateAutonomyActivity::class.java))

        }
    }

    fun callService() {
        val urlBase = "https://tfreitasf.github.io/EletricCarApi/cars.json"
        MyTask().execute(urlBase)

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

    inner class MyTask : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d("MyTask", "iniciando...")
            progrssBar.visibility = View.VISIBLE

        }

        override fun doInBackground(vararg url: String?): String {

            var urlConnection: HttpURLConnection? = null

            try {
                val urlBase = URL(url[0])

                urlConnection = urlBase.openConnection() as HttpURLConnection
                urlConnection.connectTimeout = 60000
                urlConnection.readTimeout = 6000
                urlConnection.setRequestProperty(
                    "Accept",
                    "application/json"
                )

                val responseCode = urlConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    var response = urlConnection.inputStream.bufferedReader().use { it.readText() }
                    publishProgress(response)

                } else {
                    Log.e("Erro", "Serviço indisponivel no momento")
                }


            } catch (ex: Exception) {
                Log.e("Erro", "Erro ao realizar processamento ....")


            } finally {
                urlConnection?.disconnect()
            }

            return ""
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                val jsonArray = JSONTokener(values[0]).nextValue() as JSONArray

                for (i in 0 until jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).getString("id")
                    Log.d("ID ->", id)

                    val modelName = jsonArray.getJSONObject(i).getString("modelName")
                    Log.d("Model ->", modelName)

                    val price = jsonArray.getJSONObject(i).getString("preco")
                    Log.d("Preço ->", price)

                    val battery = jsonArray.getJSONObject(i).getString("bateria")
                    Log.d("battery ->", battery)

                    val horsePower = jsonArray.getJSONObject(i).getString("potencia")
                    Log.d("horsePower ->", horsePower)

                    val recharge = jsonArray.getJSONObject(i).getString("recarga")
                    Log.d("recharge ->", recharge)

                    val urlPhoto = jsonArray.getJSONObject(i).getString("urlPhoto")
                    Log.d("urlPhoto ->", urlPhoto)

                    val model = Car(
                        id = id.toInt(),
                        modelName = modelName,
                        price = price,
                        battery = battery,
                        horsePower = horsePower,
                        recharge = recharge,
                        urlPhoto = urlPhoto
                    )
                    carsArray.add(model)
                }
                progrssBar.visibility = View.GONE
                noConnectionImage.visibility = View.GONE
                noConnectionText.visibility = View.GONE
                setupList()
            } catch (ex: Exception) {
                Log.e("Erro", ex.message.toString())
            }
        }
    }
}