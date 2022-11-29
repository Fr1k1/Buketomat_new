package com.example.buketomat.backgroundworkers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.buketomat.entites.User
import org.json.JSONException


object NetworkService {
    private const val  baseurl: String = "https://buketomatdb.000webhostapp.com/"                       //server url

    fun getUsers(callback: VolleyCallback,context : Context)                            //callback param is used to await data before doing something with it
    {
        val queue  = Volley.newRequestQueue(context)                                     //this is list of all request - it should probably be global in the future (btw requests can be canceled)
        val url = baseurl + "Test.php"
        val users : MutableList<User> = mutableListOf()
        val  jsonRequest = JsonArrayRequest(Request.Method.GET ,url,null,     //defines new request(method,url,success and failure callback functions)
            { response ->
                try {                                                                   //try-catch is here to prevent crashes caused by wrong data format
                    for (i in 0 until response.length()) {
                        val userRaw = response.getJSONObject(i)
                        users.add(User(userRaw))
                    }
                    callback.onSuccess(users)                                            //tell parent that data is ready
                }
                catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            {Log.d("API", "Something went wrong while establishing connection to server") })
        queue.add(jsonRequest)
    }





}