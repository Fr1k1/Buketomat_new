package com.example.buketomat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.models.Flower
import com.example.buketomat.models.Order
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FlowerAdapter(private val flowerList: ArrayList<Flower>): RecyclerView.Adapter<FlowerAdapter.OrderViewHolder>()  {
    inner class OrderViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        // private val flowerId : TextView
        private val flowerName: TextView
        private val flowerPrice: TextView

        init {
            // flowerId = view.findViewById(R.id.tv)
            flowerName = view.findViewById(R.id.tv_flower_name)
            flowerPrice = view.findViewById(R.id.tv_flower_price)
        }

        fun bind(flower: Flower) {
            // flowerId.text = "Id: " + flower.Id.toString()
            flowerName.text = flower.Name
            flowerPrice.text = flower.Price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerAdapter.OrderViewHolder {
        val flowerView = LayoutInflater.from(parent.context).inflate(R.layout.flower_list_item,parent,false)
        return  OrderViewHolder(flowerView)
    }

    override fun onBindViewHolder(holder: FlowerAdapter.OrderViewHolder, position: Int) {
        holder.bind(flowerList[position])
    }

    override fun getItemCount(): Int {
        return flowerList.size
    }
}