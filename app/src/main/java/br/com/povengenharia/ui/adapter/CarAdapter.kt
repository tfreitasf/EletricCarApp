package br.com.povengenharia.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.povengenharia.R
import br.com.povengenharia.domain.Car

class CarAdapter(private val cars: List<Car>) :
    RecyclerView.Adapter<CarAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_car_item, parent, false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.price.text = cars[position].price
        holder.battery.text = cars[position].battery
        holder.power.text = cars[position].horsePower
        holder.recharge.text = cars[position].recharge



    }

    override fun getItemCount(): Int = cars.size
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val price: TextView
        val battery : TextView
        val power : TextView
        val recharge : TextView

        init {
            view.apply {
                price = view.findViewById(R.id.tv_pricevalue)
                battery = view.findViewById(R.id.tv_batteryvalue)
                power = view.findViewById(R.id.tv_powervalue)
                recharge = view.findViewById(R.id.tv_rechargevalue)
            }
        }



    }

}



