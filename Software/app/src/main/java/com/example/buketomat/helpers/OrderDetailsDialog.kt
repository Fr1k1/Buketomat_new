package com.example.buketomat.helpers

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.adapters.OrderBouquetsAdapter
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.backgroundworkers.OrderBouquetsSync
import com.example.buketomat.models.Order
import com.example.buketomat.models.OrderBouquet

class OrderDetailsDialog : OrderBouquetsSync {
    val view : View
    val order : Order
    constructor(view: View,order : Order)
    {
     this.view = view
     this.order = order
     NetworkService.getBouquetsFromOrder(this, view.context,order.Id)
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

    fun setOrderDate()
    {
        val tvOrderDate : TextView= view.findViewById(R.id.tv_order_details_order_date)
        val baseText : String = view.context.resources.getString(R.string.datum_narudzbe);
        tvOrderDate.text = baseText + order.getOrderDate();
    }

    fun setDeliveryDate()
    {
        val tvDeliverDate : TextView= view.findViewById(R.id.tv_order_details_deliver_date)
        val baseText : String = view.context.resources.getString(R.string.datum_dostave);
        if(order.DeliveryTime!=null)
            tvDeliverDate.text = baseText + order.getDeliveryDate();
        else
            tvDeliverDate.text = baseText + "Nije postavljeno";
    }
    override fun showOrderItems(result: MutableList<OrderBouquet>) {
        val rvOrderItems : RecyclerView= view.findViewById(R.id.rvOrderDetails_bouquets)
        val bouquetAdapter = OrderBouquetsAdapter(result as ArrayList<OrderBouquet>)
        rvOrderItems.layoutManager = LinearLayoutManager(view.context)
        rvOrderItems.adapter = bouquetAdapter
    }


}