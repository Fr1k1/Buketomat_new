package com.example.buketomat.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buketomat.MainActivity
import com.example.buketomat.R
import com.example.buketomat.adapters.ShoppingCartAdapter
import com.example.buketomat.helpers.NewOrderDialog
import com.example.buketomat.helpers.OrderDetailsDialog
import com.example.buketomat.models.OrderBouquet

class ShopingCardFragment : Fragment() {

    private lateinit var rvShoppingItems : RecyclerView
    private lateinit var tvFinalPrice : TextView
    private lateinit var btnContinue : Button
    private lateinit var shoppingItems : MutableList<OrderBouquet>
    private var total : Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnContinue = view.findViewById(R.id.btnContinue)
        var newOrderDialogView : View
        newOrderDialogView = LayoutInflater.from(view.context).inflate(R.layout.order_finalization, null)
        btnContinue.setOnClickListener {
            AlertDialog.Builder(view.context)//create order dialog
                .setView(newOrderDialogView)
                    //removes view to preventing it from crashing when reopening
                .setOnDismissListener {
                    val parent = newOrderDialogView.parent as ViewGroup
                    parent?.removeView(newOrderDialogView)
                }
                .show()

            //configure newOrderDialog
            var newOrderDialog : NewOrderDialog? = null;
            newOrderDialog = NewOrderDialog(newOrderDialogView,shoppingItems,total)
            newOrderDialog.configureCalendar()
            newOrderDialog.setTotal();
        }
    }

    override fun onResume() {
        super.onResume()
        createList();
        calculateTotal();
        //if there is no items in shopping cart,make buttons invisible and tell user that cart is empty
        if(shoppingItems.size > 0){
            btnContinue.visibility = View.VISIBLE;
            tvFinalPrice.visibility = View.VISIBLE;
        }
        else
        {
            btnContinue.visibility = View.INVISIBLE;
            tvFinalPrice.visibility = View.INVISIBLE;
            Toast.makeText(context,"Košarica je prazna!!!", Toast.LENGTH_LONG).show();
        }


        shoppingItems.forEach {
            Log.i("OrderItems",it.Name + " " + it.kolicina);
        }
    }

    public fun calculateTotal() {
        tvFinalPrice = requireView().findViewById(R.id.tv_new_order_finalPrice)
        //reset total so it doesn't accumulate on resume
        total = 0.0
        shoppingItems.forEach {
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
        shoppingItems = activity.shoppingItems
        rvShoppingItems = requireView().findViewById(R.id.rvShoppingItems)
        val shoppingCartAdapter = ShoppingCartAdapter(shoppingItems,activity,this)
        rvShoppingItems.layoutManager = LinearLayoutManager(requireView().context)
        rvShoppingItems.adapter = shoppingCartAdapter
    }




}