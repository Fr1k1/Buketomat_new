package com.example.buketomat.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.buketomat.MainActivity
import com.example.buketomat.R
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.backgroundworkers.UsersSync
import com.example.buketomat.entites.User


class LoginFragment : Fragment() , UsersSync  {     //UsersSync is interface that is used to tell this class when data has been recived

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
        etKorime = view.findViewById(R.id.etKorime)
        etLozinka = view.findViewById(R.id.etLozinka)

        btnLogin.setOnClickListener{
            NetworkService.getUsers(etKorime.text.toString(),etLozinka.text.toString(),this, requireContext())
        }
    }

    override fun onUsersReceived(result: MutableList<User>) {
        if(result.size > 0){
            val user : User = result[0]
            Toast.makeText(context, "uspjesna prijava ", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.context, MainActivity::class.java)
            intent.putExtra("user",user);
            startActivity(intent)
        }else{
            Toast.makeText(context, "neuspjesna prijava ", Toast.LENGTH_SHORT).show()
        }
    }
}