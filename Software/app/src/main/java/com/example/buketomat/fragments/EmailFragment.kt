package com.example.buketomat.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.buketomat.R


class EmailFragment : Fragment() {


    lateinit var editTextSubject: EditText
    lateinit var editTextMessage: EditText
    lateinit var buttonSend: Button
    lateinit var subject: String
    lateinit var message: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_email, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        editTextSubject = view.findViewById(R.id.editTextSubject)
        editTextMessage = view.findViewById(R.id.editTextMessage)
        buttonSend = view.findViewById(R.id.buttonSend)
        buttonSend.setOnClickListener {
            getData()
            val intent = Intent(Intent.ACTION_SEND)
            // intent.data= Uri.parse("mailto:")

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("buketomat@gmail.com"))

            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.type = "message/rfc822"
            startActivity(Intent.createChooser(intent, "Select email"))
        }
    }

    private fun getData() {

        subject = editTextSubject.text.toString()
        message = editTextMessage.text.toString()
    }


}