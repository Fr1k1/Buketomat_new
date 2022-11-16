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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var btnLogin : Button;
    lateinit var etKorime : EditText;
    lateinit var etLozinka : EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    var lista: MutableList <User> = mutableListOf(
        User("mmarkic1@gmail.com", "mmarkic", "1234"),
        User("iivic1@gmail.com", "iivic", "5678"),
        User("ggabric1@gmail.com", "ggabric", "1111"),)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btnLogin = view.findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener{
            Log.i("prijava", "klik")
            etKorime = view.findViewById(R.id.etKorime)
            etLozinka = view.findViewById(R.id.etLozinka)
            Log.i("podaci", etKorime.text.toString() + " --- " + etLozinka.text.toString())

            var pronaden = false;
            for (element in lista){
                if (element.username == etKorime.text.toString() && element.password == etLozinka.text.toString()){
                    Toast.makeText(this.context, "Uspjesno prijavljen!", Toast.LENGTH_LONG).show()
                    pronaden = true
                }
            }
            if(pronaden == false){
                Toast.makeText(this.context, "Neuspjesna prijava!", Toast.LENGTH_LONG).show()
            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}