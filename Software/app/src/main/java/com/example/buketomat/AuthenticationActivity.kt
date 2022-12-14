package com.example.buketomat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.example.buketomat.adapters.MainPageAdapter
import com.example.buketomat.fragments.LoginFragment
import com.example.buketomat.fragments.RegistrationFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AuthenticationActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        loadTabs()
    }

    private fun loadTabs()
    {
        tabLayout= findViewById(R.id.auth_activity_tabs)
        viewPager= findViewById(R.id.auth_activity_viewpager)

        val authPagerAdapter = MainPageAdapter(supportFragmentManager, lifecycle)

        authPagerAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.registration, R.drawable.ic_launcher_background,RegistrationFragment::class))
        authPagerAdapter.AddFragment(MainPageAdapter.FragmentItem(R.string.login,R.drawable.ic_launcher_background,LoginFragment::class))

        viewPager.adapter = authPagerAdapter

        TabLayoutMediator(tabLayout,viewPager){
                tab,position ->
            tab.setText(authPagerAdapter.fragmentItems[position].titleRes)
            tab.setIcon(authPagerAdapter.fragmentItems[position].iconRes)
        }.attach()
    }


}