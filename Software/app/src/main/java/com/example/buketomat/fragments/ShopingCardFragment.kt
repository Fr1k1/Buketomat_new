package com.example.buketomat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.MainActivity
import com.example.buketomat.R
import com.example.buketomat.adapters.ShoppingCartAdapter
import com.example.buketomat.models.OrderBouquet

class ShopingCardFragment : Fragment() {

    private lateinit var rvShoppingItems : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        var activity = activity as MainActivity;
        activity.shoppingItems.forEach {
            Log.i("OrderItems",it.Name + " " + it.kolicina);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoping_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createList();
    }

    private fun createList()
    {
        var activity = activity as MainActivity;
        rvShoppingItems = requireView().findViewById(R.id.rvShoppingItems)
        //val orderAdapter = OrdersAdapter(MockDataLoader.getDemoDataOrders())
        val shoppingCartAdapter = ShoppingCartAdapter(activity.shoppingItems,activity)
        rvShoppingItems.layoutManager = LinearLayoutManager(requireView().context)
        rvShoppingItems.adapter = shoppingCartAdapter
    }


}