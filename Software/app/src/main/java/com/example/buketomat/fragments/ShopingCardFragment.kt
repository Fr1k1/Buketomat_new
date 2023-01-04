package com.example.buketomat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.MainActivity
import com.example.buketomat.R

class ShopingCardFragment : Fragment() {

    private lateinit var rvBouquets : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var activity = activity as MainActivity;
        activity.bouquets.forEach {
            Log.i("OrderItems",it.Name);
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoping_card, container, false)
    }

    fun createList()
    {

    }


}