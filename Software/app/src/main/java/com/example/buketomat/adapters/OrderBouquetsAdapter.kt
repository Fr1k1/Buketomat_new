package com.example.buketomat.adapters

import com.example.buketomat.models.OrderBouquet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R

import com.squareup.picasso.Picasso


class OrderBouquetsAdapter(private val orderBouquets : ArrayList<OrderBouquet>):
    RecyclerView.Adapter<OrderBouquetsAdapter.OrderBouquetViewHolder>() {

    inner class OrderBouquetViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val bouquetName: TextView
        private val bouquetDescription: TextView
        private val bouquetPrice: TextView
        private var bouquetImage: ImageView
        private val bouquetCount : TextView


        init {
            bouquetName = view.findViewById(R.id.tv_bouquetName)
            bouquetDescription = view.findViewById(R.id.tv_bouquetDescription)
            bouquetPrice = view.findViewById(R.id.tv_bouquetPrice)
            bouquetImage = view.findViewById(R.id.image_view)
            bouquetCount = view.findViewById(R.id.tv_numOfBouquetsOrderd)

        }


        fun bind(bouquet: OrderBouquet) {


            bouquetName.text = bouquet.Name.toString()
            bouquetDescription.text = bouquet.Description.toString()
            bouquetPrice.text = bouquet.Price.toString() + " EUR"
            if(bouquet.kolicina > 1)
            bouquetCount.text = bouquet.kolicina.toString() + " komada";
            else
                bouquetCount.text = bouquet.kolicina.toString() + " komad";
            //bouquet.Picture="https://images.pexels.com/photos/56866/garden-rose-red-pink-56866.jpeg?cs=srgb&dl=pexels-pixabay-56866.jpg&fm=jpg"

            Picasso.with(bouquetImage.context).load(bouquet.Picture).into(bouquetImage)


        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderBouquetsAdapter.OrderBouquetViewHolder {
        val orderBouquetsView =
            LayoutInflater.from(parent.context).inflate(R.layout.order_details_bouquet_list_item, parent, false)
        return OrderBouquetViewHolder(orderBouquetsView)
    }

    override fun onBindViewHolder(holder: OrderBouquetViewHolder, position: Int) {
        holder.bind(orderBouquets[position])
    }
    override fun getItemCount(): Int {
        return orderBouquets.size
    }



}