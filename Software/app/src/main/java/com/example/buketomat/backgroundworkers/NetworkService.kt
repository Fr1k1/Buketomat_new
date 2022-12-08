package com.example.buketomat.backgroundworkers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.buketomat.entites.User
import com.example.buketomat.models.Order
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


object NetworkService {
    private const val baseurl: String =
        "https://buketomatdb.000webhostapp.com/"                       //server url

    fun getUsers(
        korisnikKorime : String,
        korisnikLozinka : String,
        callback: UsersSync,
        context: Context
    )                            //callback param is used to await data before doing something with it
    {
        val queue =
            Volley.newRequestQueue(context)                                    //this is list of all request - it should probably be global in the future (btw requests can be canceled)
        val url = baseurl + "GetUser.php"
        val users: MutableList<User> = mutableListOf()
        val jsonUser = JSONObject()
        jsonUser.put("korime", korisnikKorime)
        jsonUser.put("lozinka", korisnikLozinka)

        val requestBody = JSONArray().put(jsonUser)

        Log.d("JSON", requestBody.toString())

        val jsonRequest = JsonArrayRequest(Request.Method.POST,
            url,
            requestBody,     //defines new request(method,url,success and failure callback functions)
            {
                    response ->Log.d("API", response.toString())
                try {                                                                   //try-catch is here to prevent crashes caused by wrong data format
                    for (i in 0 until response.length()) {
                        val orderRaw = response.getJSONObject(i)
                        users.add(User(orderRaw))
                    }
                                                             //tell parent that data is ready
                } catch (e: JSONException) {
                    e.printStackTrace()
                } finally {
                    callback.onUsersReceived(users)
                }
            },
            {  error -> Log.d("API", "Something went wrong while establishing connection to server")
                Log.e("Volly Error", error.toString()); // javlja detalje errora kaj ne valja
            })

        queue.add(jsonRequest)
    }


    fun getOrders(
        korisnikId: Int,
        callback: OrdersSync,
        context: Context
    )            //callback param is used to await data before doing something with it
    {
        val queue =
            Volley.newRequestQueue(context)                                    //this is list of all request - it should probably be global in the future (btw requests can be canceled)
        val url = baseurl + "GetOrders.php"
        val orders: MutableList<Order> = mutableListOf()

        val jsonUser = JSONObject().put("korisnik_id", korisnikId)
        val requestBody = JSONArray().put(jsonUser)
        Log.d("JSON", requestBody.toString())

        val jsonRequest = JsonArrayRequest(Request.Method.POST, url, requestBody,
            { response ->
                Log.d("API", response.toString())
                try {                                                                   //try-catch is here to prevent crashes caused by wrong data format
                    for (i in 0 until response.length()) {
                        val orderRaw = response.getJSONObject(i)
                        orders.add(Order(orderRaw))
                    }
                    callback.AddOrdersToList(orders)                                           //tell parent that data is ready
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                Log.d("API", "Something went wrong while establishing connection to server")
                Log.e("Volly Error", error.toString());
            })
        queue.add(jsonRequest)
    }

    fun addUser(
        korisnik: User,
        callback: UsersSync,
        context: Context
    )            //callback param is used to await data before doing something with it
    {
        val queue =
            Volley.newRequestQueue(context)                                    //this is list of all request - it should probably be global in the future (btw requests can be canceled)
        val url = baseurl + "InsertUser.php"
        val users: MutableList<User> = mutableListOf()

        val jsonUser = JSONObject()
        jsonUser.put("ime", korisnik.name)
        jsonUser.put("prezime", korisnik.surname)
        jsonUser.put("email", korisnik.email)
        jsonUser.put("lozinka", korisnik.password)
        jsonUser.put("adresa", korisnik.address)
        jsonUser.put("korime", korisnik.username)


        val requestBody = JSONArray().put(jsonUser)
        Log.d("JSON", requestBody.toString())

        val jsonRequest = JsonArrayRequest(Request.Method.POST, url, requestBody, //kasnije se moze dodati provjera dal je success
            //baza vraca duplicate entry za npr. unique mail..searcha se json objekt po error ili success....to stignem kasnije

            { response ->
                Log.d("API", response.toString())

            },
            { error ->
                Log.d("API", "Something went wrong while establishing connection to server")
                Log.e("Volly Error", error.toString());
            })
        queue.add(jsonRequest)
    }


}