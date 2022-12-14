package com.example.buketomat.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.helpers.OrderDetailsDialog
import com.example.buketomat.models.Order
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class OrdersAdapter(private val ordersList: ArrayList<Order>):RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(view : View) : RecyclerView.ViewHolder(view)
    {
        private val orderId : TextView
        private val orderDate : TextView
        private val orderFinalPrice : TextView

        init
        {
            orderId = view.findViewById(R.id.tv_orderId)
            orderDate = view.findViewById(R.id.tv_order_date)
            orderFinalPrice = view.findViewById(R.id.tv_order_final_price)

            view.setOnLongClickListener {
                val currentOrder = ordersList[adapterPosition]
                val orderDetailsDialogView = LayoutInflater.from(view.context).inflate(R.layout.order_details_dialog, null)
                AlertDialog.Builder(view.context)
                    .setView(orderDetailsDialogView)
                    .setPositiveButton(view.resources.getString(R.string.zatvori)){ _, _ ->

                    }
                    .show()
                val orderDetailsDialog = OrderDetailsDialog(orderDetailsDialogView,currentOrder)
                orderDetailsDialog.setOrderNum()
                orderDetailsDialog.setOrderValue();
                orderDetailsDialog.setOrderDate()
                orderDetailsDialog.setDeliveryDate()

                return@setOnLongClickListener true
            }
        }

        fun bind(order : Order)
        {
            orderId.text = "Br: " + order.Id.toString()
            orderDate.text = "Datum: " + order.getOrderDate();
            orderFinalPrice.text =   order.FinalPrice.toString() + "EUR"
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val orderView = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item,parent,false)
        return  OrderViewHolder(orderView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(ordersList[position])
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }
}