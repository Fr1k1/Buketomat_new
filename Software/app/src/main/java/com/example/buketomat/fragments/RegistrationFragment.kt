package com.example.buketomat.fragments

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
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


class RegistrationFragment : Fragment() {


    lateinit var gumb: Button

    lateinit var korime: EditText

    lateinit var lozinka: EditText

    lateinit var Mail: EditText


    /*fun getDemoData(): List<User> = listOf(
        User("mmarkic1@gmail.com", "mmarkic", "1234"),
        User("iivic1@gmail.com", "iivic", "5678"),
        User("ggabric1@gmail.com", "ggabric", "1111"),
    )*/

    var lista: MutableList <User> = mutableListOf(User("mmarkic1@gmail.com", "mmarkic", "1234"),
        User("iivic1@gmail.com", "iivic", "5678"),
        User("ggabric1@gmail.com", "ggabric", "1111"),)




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        gumb = view.findViewById(R.id.btn_Register)
        korime = view.findViewById(R.id.korime_txt)
        lozinka = view.findViewById(R.id.password_txt)
        Mail = view.findViewById(R.id.email_txt)








        gumb.setOnClickListener {

            var korisnik = User(
                username = korime.text.toString(),
                password = lozinka.text.toString(),
                email = Mail.text.toString()
            )

            lista.add(korisnik)
            for(element in lista) {
                Log.i("korisnik",element.toString())
                println(lista)

            }

        }
    }


}