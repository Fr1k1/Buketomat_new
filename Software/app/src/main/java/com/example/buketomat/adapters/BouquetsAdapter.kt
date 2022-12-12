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
            //bouquet.Picture="https://images.pexels.com/photos/56866/garden-rose-red-pink-56866.jpeg?cs=srgb&dl=pexels-pixabay-56866.jpg&fm=jpg"

            Picasso.with(bouquetImage.context).load(bouquet.Picture).into(bouquetImage)
                                                    //bouquet.slika tu saljem..mozda treba napraviti jos jedan textview v kojem bu taj link

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