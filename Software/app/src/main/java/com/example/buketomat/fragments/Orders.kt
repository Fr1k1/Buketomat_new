package com.example.buketomat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.adapters.OrdersAdapter
import com.example.buketomat.helpers.MockDataLoader

class Orders : Fragment() {

    private  lateinit var rvOrders : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        rvOrders = view.findViewById(R.id.rvOrders)
        val orderAdapter = OrdersAdapter(MockDataLoader.getDemoDataOrders())
        rvOrders.layoutManager = LinearLayoutManager(view.context)
        rvOrders.adapter = orderAdapter
        //LoadOrdersList(view)
    }

   /*private fun LoadOrdersList(view : View){

    }*/



}