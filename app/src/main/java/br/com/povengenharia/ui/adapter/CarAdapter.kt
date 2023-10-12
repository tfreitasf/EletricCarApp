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
        Picasso.get().load(car.urlPhoto).into(holder.imagege)




    }

    override fun getItemCount(): Int = cars.size
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val modelName: TextView
        val price: TextView
        val battery : TextView
        val power : TextView
        val recharge : TextView
        val imagege : ImageView

        init {
            view.apply {
                modelName = view.findViewById(R.id.tv_model_name)
                price = view.findViewById(R.id.tv_pricevalue)
                battery = view.findViewById(R.id.tv_batteryvalue)
                power = view.findViewById(R.id.tv_powervalue)
                recharge = view.findViewById(R.id.tv_rechargevalue)
                imagege = view.findViewById(R.id.iv_models)
            }
        }



    }

}



