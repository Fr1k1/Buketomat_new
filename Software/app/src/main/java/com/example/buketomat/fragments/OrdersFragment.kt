package com.example.buketomat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.MainActivity
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
            // hardcoded for testing purposes

        //LoadOrdersList(view)

    }

    override fun onResume() {
        super.onResume()
        var activity = activity as MainActivity
        Log.i("User",activity.user.id.toString())
        NetworkService.getOrders(activity.user.id,this,requireContext())
    }

    override fun AddOrdersToList(result: MutableList<Order>) {
            rvOrders = requireView().findViewById(R.id.rvOrders)
            //val orderAdapter = OrdersAdapter(MockDataLoader.getDemoDataOrders())
            val orderAdapter = OrdersAdapter(result as ArrayList<Order>)
            rvOrders.layoutManager = LinearLayoutManager(requireView().context)
            rvOrders.adapter = orderAdapter
    }

    /*private fun LoadOrdersList(view : View){

     }*/



}