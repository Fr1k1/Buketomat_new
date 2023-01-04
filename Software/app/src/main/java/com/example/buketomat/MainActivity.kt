package com.example.buketomat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.buketomat.adapters.MainPageAdapter
import com.example.buketomat.backgroundworkers.BouquetClickListener
import com.example.buketomat.entites.User
import com.example.buketomat.fragments.*
import com.example.buketomat.models.OrderBouquet
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() , BouquetClickListener {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    lateinit  var user : User
    var shoppingItems : MutableList<OrderBouquet>  = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNavigation()
        createUser()

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


    private fun createNavigation()
    {
        tabLayout= findViewById(R.id.tabs)
        viewPager= findViewById(R.id.viewpager)

        val mainPageAdapter = MainPageAdapter(supportFragmentManager, lifecycle)


        /*mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.registration, R.drawable.ic_launcher_background,RegistrationFragment::class))
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.login,R.drawable.ic_launcher_background,LoginFragment::class))
        */
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.mail_fragment,R.drawable.ic_baseline_shopping_bag_24,EmailFragment::class))
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.finished_bouquets_fragment,R.drawable.ic_baseline_shopping_bag_24,FinishedBouquetsFragment::class))
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.orders,R.drawable.ic_baseline_shopping_bag_24,OrdersFragment::class))
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.new_order,R.drawable.ic_baseline_add_24,NewBouquetFragment::class))
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.shopingCart,R.drawable.ic_baseline_shopping_bag_24,ShopingCardFragment::class))


        viewPager.adapter = mainPageAdapter

        TabLayoutMediator(tabLayout,viewPager){
                tab,position ->
            tab.setText(mainPageAdapter.fragmentItems[position].titleRes)
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


}