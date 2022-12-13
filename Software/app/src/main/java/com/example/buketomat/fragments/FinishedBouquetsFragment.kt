package com.example.buketomat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.MainActivity
import com.example.buketomat.R
import com.example.buketomat.adapters.BouquetsAdapter
import com.example.buketomat.adapters.OrdersAdapter
import com.example.buketomat.backgroundworkers.BouquetsSync
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.models.Bouquet
import com.example.buketomat.models.Order


class FinishedBouquetsFragment : Fragment(), BouquetsSync {

    private lateinit var rvFinishedBouquets: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_finished_bouquets, container, false)
    }

    override fun onResume() {
        super.onResume()
        var activity = activity as MainActivity
        NetworkService.getBouquets(this,requireContext())
    }

    override fun AddBouquetsToList(result: MutableList<Bouquet>) {
        rvFinishedBouquets = requireView().findViewById(R.id.rvFinishedBouquets)

        val bouquetAdapter = BouquetsAdapter(result as ArrayList<Bouquet>)
        rvFinishedBouquets.layoutManager = LinearLayoutManager(requireView().context)
        rvFinishedBouquets.adapter = bouquetAdapter
    }


}