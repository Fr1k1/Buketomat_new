package com.example.buketomat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.buketomat.backgroundworkers.BouquetClickListener
import com.example.buketomat.entites.User
import com.example.buketomat.fragments.ShopingCardFragment
import com.example.buketomat.models.OrderBouquet


class ShoppingCartActivity : AppCompatActivity() , BouquetClickListener {

    lateinit var shoppingItems : MutableList<OrderBouquet>
    lateinit  var user : User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoping_cart)
        shoppingItems = intent.getParcelableArrayListExtra<OrderBouquet>("shoppingCart") as MutableList<OrderBouquet>
        shoppingItems.forEach {
            Log.i("OrderItems",it.Name + " " + it.kolicina);
        }
        user = intent.getParcelableExtra<User>("user")!!
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = ShopingCardFragment()
        fragmentTransaction.add(R.id.fcv_shopping_cart, fragment)
        fragmentTransaction.commit()


    }
    override fun addBouquetToOrder(orderItem: OrderBouquet) {
        //addes order item to the list, if item already exist then just increase amount of it
        val existingItem = (shoppingItems.find { it.Id == orderItem.Id })
        if(existingItem != null)
        {
            existingItem.kolicina++;
            shoppingItems[shoppingItems.indexOf(existingItem)] = existingItem;
        }
        else
            shoppingItems.add(orderItem);
    }

    override fun removeBouquetFromOrder(orderItem: OrderBouquet) {
        shoppingItems.remove(orderItem);
    }


    fun showOrdersFragment()
    {

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("methodToCall", "orderList")
        intent.putExtra("user",user)
        startActivity(intent)
        finish()

    }

}