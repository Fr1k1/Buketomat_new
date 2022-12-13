package com.example.buketomat.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.buketomat.R
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.backgroundworkers.UsersSync
import com.example.buketomat.entites.User


class RegistrationFragment : Fragment(), UsersSync {


    lateinit var gumb: Button

    lateinit var ime: EditText

    lateinit var prezime: EditText

    lateinit var adresa: EditText

    lateinit var korime: EditText

    lateinit var lozinka: EditText

    lateinit var Mail: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        gumb = view.findViewById(R.id.btn_Register)
        ime = view.findViewById(R.id.txtIme)
        prezime = view.findViewById(R.id.txtPrezime)
        adresa = view.findViewById(R.id.txtAdresa)
        korime = view.findViewById(R.id.korime_txt)
        lozinka = view.findViewById(R.id.password_txt)
        Mail = view.findViewById(R.id.email_txt)

        gumb.setOnClickListener {
            var korisnik = User(
                id = -1,
                name = ime.text.toString(),
                surname = prezime.text.toString(),
                address = adresa.text.toString(),
                email = Mail.text.toString(),
                username = korime.text.toString(),
                password = lozinka.text.toString(),
            )

            if (isValidEmail(Mail.text.toString())) {
                NetworkService.addUser(korisnik, this, requireContext())
                Toast.makeText(context, "Registracija uspjesna", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(context, "Krivi mail", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onUsersReceived(result: MutableList<User>) {
        Toast.makeText(context, "Registracija uspjesna", Toast.LENGTH_SHORT).show()
    }

    fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


}