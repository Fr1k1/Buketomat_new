package com.example.buketomat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.R
import com.example.buketomat.adapters.OrdersAdapter
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.backgroundworkers.OrdersSync
import com.example.buketomat.helpers.MockDataLoader
import com.example.buketomat.models.Order

class OrdersFragment : Fragment() , OrdersSync {

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
        NetworkService.getOrders(1,this,view.context)     // hardcoded for testing purposes
        rvOrders = view.findViewById(R.id.rvOrders)
        val orderAdapter = OrdersAdapter(MockDataLoader.getDemoDataOrders())
        rvOrders.layoutManager = LinearLayoutManager(view.context)
        rvOrders.adapter = orderAdapter
        //LoadOrdersList(view)

    }

    override fun onOrdersReceived(result: MutableList<Order>) {

        result.forEach{
            Toast.makeText(this.context,it.FinalPrice.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    /*private fun LoadOrdersList(view : View){

     }*/



}