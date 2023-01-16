package com.example.buketomat.helpers

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.adapters.OrderBouquetsAdapter
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.backgroundworkers.NewOrderSync
import com.example.buketomat.backgroundworkers.OrderBouquetsSync
import com.example.buketomat.entites.User
import com.example.buketomat.fragments.ShopingCardFragment
import com.example.buketomat.models.Order
import com.example.buketomat.models.OrderBouquet
import java.time.LocalDate
import java.util.*

class NewOrderDialog : NewOrderSync {

        val view : View
        val orderItems : MutableList<OrderBouquet>
        val total : Double
        val User : User
        var counter = 0;
        val parent : ShopingCardFragment
        var  selectedDate : Date = Calendar.getInstance().time
        private lateinit var calDeliveryDate : CalendarView
        private lateinit var etAdress : EditText
        constructor(frag : ShopingCardFragment, view: View, orderItems : MutableList<OrderBouquet>,total : Double,User : User)
        {
            this.view = view
            this.orderItems = orderItems
            this.total = total
            this.User = User
            this.parent = frag
            calDeliveryDate =  view.findViewById(R.id.calDateOfDelivery)
            etAdress = view.findViewById(R.id.etPostAdress)
            val btnOrder : Button = view.findViewById(R.id.btnOrder)

            calDeliveryDate.setOnDateChangeListener { view, year, month, dayOfMonth ->
                // Use the year, month, and dayOfMonth variables to create a Calendar object
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                selectedDate = calendar.time
            }

            btnOrder.setOnClickListener {

                Log.i("Date",selectedDate.toString());
                NetworkService.addOrder(Order(-1,total,selectedDate,etAdress.text.toString(),User),this,view.context)

            }
        }

        fun configureCalendar()
        {

            val c = Calendar.getInstance()
            val currentDate = c.time
            calDeliveryDate.minDate = currentDate.time
        }
        fun setTotal()
        {
        val tvTotal : TextView= view.findViewById(R.id.tv_final_order_price)
        tvTotal.text = total.toString() + " EUR"
        }

    override fun onOrderAdded(orderId: Int) {
        Toast.makeText(view.context,"Order inserted",Toast.LENGTH_SHORT).show();
        // add every item to db and assign it orderId
        orderItems.forEach { item ->
            NetworkService.addOrderItem(item,orderId,this,view.context)
        }
    }

    override fun onOrderItemAdded() {
        counter++
        if(counter == orderItems.size)  //if all items are added then say it was successful
        {
            Toast.makeText(view.context,"Order items inserted successfully",Toast.LENGTH_SHORT).show();
            //reset back to 0,probably not needed
            counter = 0;
            //clear shopping cart
            parent.cleanUpAfterOrdering()

        }

    }


}