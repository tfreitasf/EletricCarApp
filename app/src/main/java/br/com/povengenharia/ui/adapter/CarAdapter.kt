package br.com.povengenharia.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.R
import br.com.povengenharia.domain.Car
import com.squareup.picasso.Picasso

class CarAdapter(private val cars: List<Car>) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {

    var carItemLister : (Car) -> Unit ={}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_car_item, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car =  cars[position]

        holder.modelName.text = car.modelName
        holder.price.text = car.price
        holder.battery.text = car.battery
        holder.power.text = car.horsePower
        holder.recharge.text = car.recharge

        if (!car.urlPhoto.isNullOrEmpty()) {
            Picasso.get().load(car.urlPhoto).into(holder.image)
        } else {
            // Defina uma imagem de substituição quando o URL estiver vazio.
            holder.image.setImageResource(R.drawable.ic_wifi_off)
        }

        holder.favorite.setOnClickListener {
            val carro =  cars[position]
            carItemLister(carro)
            setupFavorite(carro, holder)
        }
    }

    private fun setupFavorite(
        carro: Car,
        holder: ViewHolder
    ) {
        carro.isFavorite = !carro.isFavorite

        if (carro.isFavorite) {
            holder.favorite.setImageResource(R.drawable.ic_star_selected)
        } else {
            holder.favorite.setImageResource(R.drawable.ic_star)
        }
    }

    override fun getItemCount(): Int = cars.size
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val modelName: TextView
        val price: TextView
        val battery : TextView
        val power : TextView
        val recharge : TextView
        val image : ImageView
        val favorite : ImageView

        init {
            view.apply {
                modelName = findViewById(R.id.tv_model_name)
                price = findViewById(R.id.tv_pricevalue)
                battery = findViewById(R.id.tv_batteryvalue)
                power = findViewById(R.id.tv_powervalue)
                recharge = findViewById(R.id.tv_rechargevalue)
                image = findViewById(R.id.iv_models)
                favorite = findViewById(R.id.iv_favorite)
            }
        }



    }

}



