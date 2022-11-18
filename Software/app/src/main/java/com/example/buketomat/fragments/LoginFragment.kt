package com.example.buketomat.fragments

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


}