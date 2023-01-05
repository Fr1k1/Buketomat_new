package com.example.buketomat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.backgroundworkers.BouquetClickListener
import com.example.buketomat.fragments.ShopingCardFragment

import com.example.buketomat.models.OrderBouquet
import com.squareup.picasso.Picasso

class ShoppingCartAdapter(private val shoppingItems: MutableList<OrderBouquet>,private val callbackMain: BouquetClickListener, private val callbackShop : ShopingCardFragment) :
    RecyclerView.Adapter<ShoppingCartAdapter.ShoppingCartItemViewHolder>() {



        inner class ShoppingCartItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

            private val bouquetName: TextView
            //private val bouquetDescription: TextView
            private val bouquetPrice: TextView
            private var bouquetImage: ImageView
            private var etItemAmount : EditText


            init {
                bouquetName = view.findViewById(R.id.tv_bouquetName)
                //bouquetDescription = view.findViewById(R.id.tv_bouquetDescription)
                bouquetPrice = view.findViewById(R.id.tv_price)
                bouquetImage = view.findViewById(R.id.image_view)
                etItemAmount = view.findViewById(R.id.etItemAmount)
               // kol = view.findViewById(R.id.kolicina)

                etItemAmount.addTextChangedListener {
                    if(etItemAmount.text.toString() != "")
                    {
                        try {
                            val kolicina = etItemAmount.text.toString().toInt()
                            shoppingItems[adapterPosition].kolicina = kolicina
                            bouquetPrice.text =  (kolicina * shoppingItems[adapterPosition].Price).toString() + " EUR"
                            callbackShop.calculateTotal();
                        }
                        catch(ex : NumberFormatException){
                        }

                    }

                }


                view.setOnLongClickListener{
                    AlertDialog.Builder(view.context)
                        .setTitle("Izbriši stavku")
                        .setPositiveButton(view.resources.getString(R.string.Delete)){ _, _ ->
                            callbackMain.removeBouquetFromOrder(shoppingItems[adapterPosition])
                            notifyDataSetChanged();
                        }
                        .setNegativeButton(view.resources.getString(R.string.Cancel)){ _, _ ->

                        }
                        .show()
                    return@setOnLongClickListener true
                }

            }

            fun bind(item: OrderBouquet) {
                bouquetName.text = item.Name.toString()
                bouquetPrice.text = item.Price.toString() + " EUR"
                Picasso.with(bouquetImage.context).load(item.Picture).into(bouquetImage)
                etItemAmount.setText(item.kolicina.toString())


            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ShoppingCartItemViewHolder {
            val bouquetsView =
                LayoutInflater.from(parent.context).inflate(R.layout.shopping_cart_list_item, parent, false)
            return ShoppingCartItemViewHolder(bouquetsView)
        }

        override fun onBindViewHolder(holder: ShoppingCartItemViewHolder, position: Int) {
            holder.bind(shoppingItems[position])
        }

        override fun getItemCount(): Int {
            return shoppingItems.size
        }

}