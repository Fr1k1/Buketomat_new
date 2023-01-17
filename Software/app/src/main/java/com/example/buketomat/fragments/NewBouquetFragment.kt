package com.example.buketomat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.MainActivity
import com.example.buketomat.R
import com.example.buketomat.adapters.BouquetsAdapter
import com.example.buketomat.adapters.FlowerAdapter
import com.example.buketomat.adapters.OrdersAdapter
import com.example.buketomat.backgroundworkers.BouquetsSync
import com.example.buketomat.backgroundworkers.FlowersSync
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.entites.User
import com.example.buketomat.models.Bouquet
import com.example.buketomat.models.Flower
import com.example.buketomat.models.Order

class NewBouquetFragment : Fragment(), FlowersSync, BouquetsSync {

    // za random buket
    private lateinit var rvRandomFinishedBouquet: RecyclerView

    // za unos novog buketa
    private lateinit var rvFlowers : RecyclerView
    lateinit var btnDodajAutomatski : Button
    lateinit var btnNapraviBuket : Button

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
        btnDodajAutomatski = view.findViewById(R.id.btnDodajAutomatski)
        btnNapraviBuket = view.findViewById(R.id.btnNapraviBuket)

        btnDodajAutomatski.setOnClickListener{
            val randomBroj = (1..10).random()
            Toast.makeText(context, "Random buket ID: " + randomBroj.toString(), Toast.LENGTH_SHORT).show()
            // za random buket
            NetworkService.getBouquetById(randomBroj,this, requireContext())
        }

        btnNapraviBuket.setOnClickListener {
            var total = 0.0
            val itemCount = rvFlowers.adapter?.itemCount
            for (i in 0 until itemCount!!) {

                // prolazi kroz sve i vraca viewholder za item na toj poziciji
                val holder = rvFlowers.findViewHolderForAdapterPosition(i)
                if (holder != null) {
                    val flowerNameView = holder.itemView.findViewById<View>(R.id.tv_flower_name) as TextView
                    val flowerPriceView = holder.itemView.findViewById<View>(R.id.tv_flower_price) as TextView
                    val flowerKolicinaView = holder.itemView.findViewById<View>(R.id.etKolicina) as TextView
                    val flowerId = i+1

                    // dohvati samo kojima je kolicina promijenjena
                    if (flowerKolicinaView.text.toString().toInt() > 0 ) {
                        Toast.makeText(context, flowerPriceView.text.toString() +" Ime: " + flowerNameView.text.toString() + " id: " + flowerId + " kolicina: " + flowerKolicinaView.text.toString(), Toast.LENGTH_SHORT).show()

                        // racunanje ukupne cijene buketa
                        total += flowerKolicinaView.text.toString().toDouble() * flowerPriceView.text.toString().toDouble();
                        Toast.makeText(context, total.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            NetworkService.addBouquet(total, this, requireContext())
        }
    }

    override fun onResume() {
        super.onResume()
        NetworkService.getFlowers(this,requireContext())
    }

    // za unos novog buketa
    override fun AddFlowersToList(result: MutableList<Flower>) {
        rvFlowers = requireView().findViewById(R.id.rvFlowers)
        val flowerAdapter = FlowerAdapter(result as ArrayList<Flower>)
        rvFlowers.layoutManager = LinearLayoutManager(requireView().context)
        rvFlowers.adapter = flowerAdapter
    }

    // za random buket
    override fun AddBouquetsToList(result: MutableList<Bouquet>) {
        rvRandomFinishedBouquet = requireView().findViewById(R.id.rvRandomFinishedBouquet)
        val bouquetAdapter = BouquetsAdapter(result as ArrayList<Bouquet>,activity as MainActivity)
        rvRandomFinishedBouquet.layoutManager = LinearLayoutManager(requireView().context)
        rvRandomFinishedBouquet.adapter = bouquetAdapter
    }

    override fun onBouquetAdded(orderId: Int) {
        Toast.makeText(view?.context,"Order inserted",Toast.LENGTH_SHORT).show();
        // add every item to db and assign it orderId
        /*  orderItems.forEach { item ->
              NetworkService.addOrderItem(item,orderId,this,view.context)
          }*/
    }

    override fun onBouquetItemAdded() {
        /*counter++
        if(counter == orderItems.size)  //if all items are added then say it was successful
        {
            Toast.makeText(view.context,"Order items inserted successfully",Toast.LENGTH_SHORT).show();
            //reset back to 0,probably not needed
            counter = 0;
            //clear shopping cart
            parent.cleanUpAfterOrdering()

        }*/
    }

}