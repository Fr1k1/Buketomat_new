package com.example.buketomat.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.buketomat.R
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.backgroundworkers.VolleyCallback
import com.example.buketomat.entites.User
import com.example.buketomat.helpers.MockDataLoader

class LoginFragment : Fragment() {

    lateinit var btnLogin : Button;
    lateinit var etKorime : EditText;
    lateinit var etLozinka : EditText;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnLogin = view.findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener{
            ShowUsersToast(view.context)
            Log.i("prijava", "klik")
            etKorime = view.findViewById(R.id.etKorime)
            etLozinka = view.findViewById(R.id.etLozinka)
            Log.i("podaci", etKorime.text.toString() + " --- " + etLozinka.text.toString())

            var pronaden = false;
            var list = MockDataLoader.getDemoDataUsers()

            for(element in list) {
                Log.i("korisnik", element.toString())
                println(list)
            }
            for (element in list){
                if (element.username == etKorime.text.toString() && element.password == etLozinka.text.toString()){
                    Toast.makeText(this.context, "Uspjesno prijavljen!", Toast.LENGTH_SHORT).show()
                    pronaden = true
                }
            }
            if(pronaden == false){
                Toast.makeText(this.context, "Neuspjesna prijava!", Toast.LENGTH_SHORT).show()
            }

        }

    }
    fun ShowUsersToast(context: Context)
    {
        NetworkService.getUsers(object : VolleyCallback {           //functionality goes inside override onSuccess
            override fun onSuccess(result: Any) {
                val users = result as MutableList<User>  //dangerous cast!!! Only do if you know what you are doing(check what type is sent to OnSuccess() function in your specific case)
                users.forEach{ user ->
                    Toast.makeText(context,user.username,Toast.LENGTH_LONG).show();     //be careful when using context inside interface override
                }                                                                                //because keyword this references interface not your current activity
            }},context)
    }




}