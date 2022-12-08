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
import com.example.buketomat.MainActivity
import com.example.buketomat.R
import com.example.buketomat.backgroundworkers.NetworkService
import com.example.buketomat.backgroundworkers.UsersSync
import com.example.buketomat.entites.User
import com.example.buketomat.helpers.MockDataLoader

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

            val rezultat = NetworkService.getUsers(etKorime.text.toString(),etLozinka.text.toString(),this, requireContext())
            Toast.makeText(context, "uspjesna prijava ", Toast.LENGTH_SHORT).show()
            //if (etKorime.text.toString()==)

        /*
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
                    var activity = activity as MainActivity
                    activity.user = element;
                }

                //test
            }
            if(pronaden == false){
                Toast.makeText(this.context, "Neuspjesna prijava!", Toast.LENGTH_SHORT).show()
            }
*/
        }

    }
    fun ShowUsersToast(context: Context)
    {
        //NetworkService.getUsers(this,context)           //you can send keyword this only if current class implements VolleyCallback interface
    }

    override fun onUsersReceived(result: MutableList<User>) {
       // result.forEach{ user ->
            //Toast.makeText(context,user.username,Toast.LENGTH_LONG).show();     //be careful when using context inside interface override
       // }
    }


}