package com.example.buketomat.helpers

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.adapters.BouquetsAdapter
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.backgroundworkers.OrderBouquetsSync
import com.example.buketomat.models.Bouquet
import com.example.buketomat.models.Order
import com.example.buketomat.models.OrderBouquet

class OrderDetailsDialog : OrderBouquetsSync {
    val view : View
    val order : Order
    constructor(view: View,order : Order)
    {
     this.view = view
     this.order = order
     NetworkService.getBouqetsFromOrder(this, view.context,order.Id)
    }
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

    override fun showOrderItems(result: MutableList<OrderBouquet>) {
        val rvOrderItems : RecyclerView= view.findViewById(R.id.rvOrderDetails_bouquets)

        val bouquetAdapter = BouquetsAdapter(result as ArrayList<Bouquet>)
        rvOrderItems.layoutManager = LinearLayoutManager(view.context)
        rvOrderItems.adapter = bouquetAdapter
    }
    

}