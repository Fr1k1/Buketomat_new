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
import com.example.buketomat.adapters.FlowerAdapter
import com.example.buketomat.adapters.OrdersAdapter
import com.example.buketomat.backgroundworkers.FlowersSync
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.models.Flower
import com.example.buketomat.models.Order

class NewBouquetFragment : Fragment(), FlowersSync {

    private lateinit var rvFlowers : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_bouquet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // za poslije
    }

    override fun onResume() {
        super.onResume()
        var activity = activity as MainActivity
        //Log.i("User",activity.user.id.toString())
        NetworkService.getFlowers(this,requireContext())
    }

    override fun AddFlowersToList(result: MutableList<Flower>) {
        rvFlowers = requireView().findViewById(R.id.rvFlowers)
        //val orderAdapter = OrdersAdapter(MockDataLoader.getDemoDataOrders())
        val flowerAdapter = FlowerAdapter(result as ArrayList<Flower>)
        rvFlowers.layoutManager = LinearLayoutManager(requireView().context)
        rvFlowers.adapter = flowerAdapter
    }

}