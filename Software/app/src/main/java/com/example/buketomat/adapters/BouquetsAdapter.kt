package com.example.buketomat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewTreeLifecycleOwner.get
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.models.Bouquet
import com.example.buketomat.models.Order
import com.squareup.picasso.Picasso

class BouquetsAdapter(private val bouquetsList: ArrayList<Bouquet>) :
    RecyclerView.Adapter<BouquetsAdapter.BouquetViewHolder>() {

    inner class BouquetViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val bouquetName: TextView
        private val bouquetDescription: TextView
        private val bouquetPrice: TextView
        private var bouquetImage:ImageView


        init {
            bouquetName = view.findViewById(R.id.tv_bouquetName)
            bouquetDescription = view.findViewById(R.id.tv_bouquetDescription)
            bouquetPrice = view.findViewById(R.id.tv_bouquetPrice)
            bouquetImage=view.findViewById(R.id.image_view)

        }



        fun bind(bouquet: Bouquet) {


            bouquetName.text = "Naziv" + bouquet.Name.toString()
            bouquetDescription.text = "Description" + bouquet.Description.toString()
            bouquetPrice.text = "Price" + bouquet.Price.toString()
            //bouquetImage. mozda budem moral slati URL
            Picasso.with(bouquetImage.context).load("https://i.pinimg.com/736x/e0/fd/dc/e0fddc39827653aa52d39026e3705847.jpg").into(bouquetImage)



        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BouquetViewHolder {
        val bouquetsView =
            LayoutInflater.from(parent.context).inflate(R.layout.bouquet_list_item, parent, false)
        return BouquetViewHolder(bouquetsView)
    }

    override fun onBindViewHolder(holder: BouquetViewHolder, position: Int) {
        holder.bind(bouquetsList[position])
    }

    override fun getItemCount(): Int {
        return bouquetsList.size
    }
}