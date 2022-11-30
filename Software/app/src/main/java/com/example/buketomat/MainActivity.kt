package com.example.buketomat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.buketomat.adapters.MainPageAdapter
import com.example.buketomat.fragments.LoginFragment
import com.example.buketomat.fragments.RegistrationFragment
import com.example.buketomat.fragments.Orders
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNavigation()

    }


    private fun createNavigation()
    {
        tabLayout= findViewById(R.id.tabs)
        viewPager= findViewById(R.id.viewpager)

        val mainPageAdapter = MainPageAdapter(supportFragmentManager, lifecycle)

        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.registration, R.drawable.ic_launcher_background,RegistrationFragment::class))
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.login,R.drawable.ic_launcher_background,LoginFragment::class))
        mainPageAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.orders,R.drawable.ic_baseline_shopping_bag_24,Orders::class))

        viewPager.adapter = mainPageAdapter

        TabLayoutMediator(tabLayout,viewPager){
                tab,position ->
            tab.setText(mainPageAdapter.fragmentItems[position].titleRes)
            tab.setIcon(mainPageAdapter.fragmentItems[position].iconRes)
        }.attach()
    }


}