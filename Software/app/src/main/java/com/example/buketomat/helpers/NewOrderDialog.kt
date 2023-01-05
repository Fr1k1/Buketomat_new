package com.example.buketomat.helpers

import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.adapters.OrderBouquetsAdapter
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.backgroundworkers.OrderBouquetsSync
import com.example.buketomat.models.Order
import com.example.buketomat.models.OrderBouquet
import java.time.LocalDate
import java.util.*

class NewOrderDialog {

        val view : View
        val orderItems : MutableList<OrderBouquet>
        val total : Double

        constructor(view: View, orderItems : MutableList<OrderBouquet>,total : Double)
        {
            this.view = view
            this.orderItems = orderItems
            this.total = total

            val btnOrder : Button = view.findViewById(R.id.btnOrder)
            btnOrder.setOnClickListener {
                Toast.makeText(view.context,"Here",Toast.LENGTH_SHORT).show();
            }
        }

        fun configureCalendar()
        {
            val calDeliveryDate : CalendarView= view.findViewById(R.id.calDateOfDelivery)
            val c = Calendar.getInstance()
            val currentDate = c.time
            calDeliveryDate.minDate = currentDate.time
        }
        fun setTotal()
        {
        val tvTotal : TextView= view.findViewById(R.id.tv_final_order_price)
        tvTotal.text = total.toString() + " EUR"
        }



}