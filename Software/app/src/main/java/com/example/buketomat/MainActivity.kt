package com.example.buketomat



import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.buketomat.adapters.MainPageAdapter
import com.example.buketomat.backgroundworkers.BouquetClickListener
import com.example.buketomat.entites.User
import com.example.buketomat.fragments.*
import com.example.buketomat.models.OrderBouquet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() , BouquetClickListener {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    lateinit  var user : User
    private lateinit var shoppingCart : FloatingActionButton
    var shoppingItems : MutableList<OrderBouquet>  = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNavigation()
        createUser()
        shoppingCart = findViewById(R.id.fab_shopping_cart)
        shoppingCart.setOnClickListener{
            if(shoppingItems.size == 0)
                Toast.makeText(this,"Košarica je prazna!!!",Toast.LENGTH_SHORT).show()
            else {
                val intent = Intent(this, ShoppingCartActivity::class.java)
                intent.putParcelableArrayListExtra("shoppingCart", ArrayList(shoppingItems))
                intent.putExtra("user",user)
                startActivity(intent)
            }
        }
        val methodToCall = intent.getStringExtra("methodToCall")
        if (methodToCall == "orderList") {
            showOrdersFragment()
        }
    }


    private fun createUser()
    {
        user = intent.getParcelableExtra<User>("user")!!

    }


    private fun createNavigation()
    {
        tabLayout= findViewById(R.id.tabs)
        viewPager= findViewById(R.id.viewpager)

        val mainPageAdapter = MainPageAdapter(supportFragmentManager, lifecycle)

        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(null,R.drawable.flower,FinishedBouquetsFragment::class))
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(null,R.drawable.icons8_flower_bouquet_100__1_,NewBouquetFragment::class))
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(null,R.drawable.ic_baseline_shopping_bag_24,OrdersFragment::class))
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(null,R.drawable.ic_baseline_email_24,EmailFragment::class))


        viewPager.adapter = mainPageAdapter

        TabLayoutMediator(tabLayout,viewPager){
                tab,position ->
                mainPageAdapter.fragmentItems[position].titleRes?.let { tab.setText(it) }
            tab.setIcon(mainPageAdapter.fragmentItems[position].iconRes)
        }.attach()
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
        //this function is called here because navigation isn't initialized at this point
        viewPager.setCurrentItem(2,true);
    }


}