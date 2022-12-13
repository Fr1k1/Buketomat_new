package com.example.buketomat.helpers

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.adapters.BouquetsAdapter
import com.example.buketomat.models.Bouquet
import com.example.buketomat.models.Order

class OrderDetailsDialog(val view: View,val order : Order) {

    fun setOrderNum()
    {

        val tvOrderNum : TextView= view.findViewById(R.id.tvOrderNum)
        val baseText : String = view.context.resources.getString(R.string.narud_ba_broj);
        tvOrderNum.text = baseText + order.Id.toString();

    }

    fun setOrderValue()
    {

        val tvOrderPrice : TextView= view.findViewById(R.id.tvOrderDetails_price)
        val baseText : String = view.context.resources.getString(R.string.iznos);
        tvOrderPrice.text = baseText + order.FinalPrice.toString();
    }

   /* fun setOrderItems()
    {
        val rvOrderItems : RecyclerView= view.findViewById(R.id.rvOrderDetails_bouquets)

        val bouquetAdapter = BouquetsAdapter(result as ArrayList<Bouquet>)
        rvOrderItems.layoutManager = LinearLayoutManager(view.context)
        rvOrderItems.adapter = bouquetAdapter
    }*/

}