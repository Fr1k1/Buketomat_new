package com.example.buketomat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.MainActivity
import com.example.buketomat.R
import com.example.buketomat.adapters.ShoppingCartAdapter
import com.example.buketomat.models.OrderBouquet

class ShopingCardFragment : Fragment() {

    private lateinit var rvShoppingItems : RecyclerView
    private lateinit var tvFinalPrice : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        var activity = activity as MainActivity;
        activity.shoppingItems.forEach {
            Log.i("OrderItems",it.Name + " " + it.kolicina);
        }
        createList();
        calculateTotal();
    }

    public fun calculateTotal() {
        tvFinalPrice = requireView().findViewById(R.id.tv_new_order_finalPrice)
        var activity = activity as MainActivity
        var total = 0.0;
        activity.shoppingItems.forEach {
            total+=it.kolicina * it.Price
        }
        tvFinalPrice.text = "UKUPNO: " + total + " EUR"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoping_card, container, false)
    }

    private fun createList()
    {
        var activity = activity as MainActivity;
        rvShoppingItems = requireView().findViewById(R.id.rvShoppingItems)
        //val orderAdapter = OrdersAdapter(MockDataLoader.getDemoDataOrders())
        val shoppingCartAdapter = ShoppingCartAdapter(activity.shoppingItems,activity,this)
        rvShoppingItems.layoutManager = LinearLayoutManager(requireView().context)
        rvShoppingItems.adapter = shoppingCartAdapter
    }


}