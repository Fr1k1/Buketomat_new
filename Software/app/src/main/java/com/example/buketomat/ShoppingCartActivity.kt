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
        createUser()
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


    private fun createUser()
    {
        val id = intent.getIntExtra("user_id",0)
        val email = intent.getStringExtra("user_email")
        val username = intent.getStringExtra("user_username")
        val password = intent.getStringExtra("user_password")
        val name = intent.getStringExtra("user_name")
        val surname = intent.getStringExtra("user_surname")
        val address = intent.getStringExtra("user_address")
        if(id > 0)
            user = User(id,name!!,surname!!,address!!,email!!,username!!,password!!)
    }

    fun showOrdersFragment()
    {

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("methodToCall", "orderList")
        intent.putExtra("user_id",user.id)
        intent.putExtra("user_email",user.email)
        intent.putExtra("user_username",user.username)
        intent.putExtra("user_password",user.password)
        intent.putExtra("user_name",user.name)
        intent.putExtra("user_surname",user.surname)
        intent.putExtra("user_address",user.address)
        startActivity(intent)
        finish()

    }

}