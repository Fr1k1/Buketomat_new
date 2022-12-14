package com.example.buketomat.backgroundworkers

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.buketomat.entites.User
import com.example.buketomat.models.Bouquet
import com.example.buketomat.models.Flower
import com.example.buketomat.models.Order
import com.example.buketomat.models.OrderBouquet
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


object NetworkService {
    private const val baseurl: String =
        "https://buketomatdb.000webhostapp.com/"                       //server url

    fun getUsers(
        korisnikKorime: String,
        korisnikLozinka: String,
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
            { response ->
                Log.d("API", response.toString())
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
            { reportError(it) })

        queue.add(jsonRequest)
    }


    fun getOrders(korisnikId: Int, callback: OrdersSync, context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url = baseurl + "GetOrders.php"
        val orders: MutableList<Order> = mutableListOf()

        val jsonUser = JSONObject().put("korisnik_id", korisnikId)
        val requestBody = JSONArray().put(jsonUser)
        Log.d("JSON", requestBody.toString())

        val jsonRequest = JsonArrayRequest(Request.Method.POST, url, requestBody,
            { response ->
                Log.d("API", response.toString())
                try {
                    for (i in 0 until response.length()) {
                        val orderRaw = response.getJSONObject(i)
                        orders.add(Order(orderRaw))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                } finally {
                    callback.AddOrdersToList(orders)

                }
            },
            { reportError(it) })
        queue.add(jsonRequest)
    }

    fun addUser(korisnik: User, callback: UsersSync, context: Context) {
        val queue = Volley.newRequestQueue(context)
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

        val jsonRequest = JsonArrayRequest(Request.Method.POST,
            url,
            requestBody, //kasnije se moze dodati provjera dal je success
            //baza vraca duplicate entry za npr. unique mail..searcha se json objekt po error ili success...

            { response ->
                Log.d("API", response.toString())

            },
            { reportError(it) })
        queue.add(jsonRequest)
    }

    fun getFlowers(callback: FlowersSync, context: Context) {
        val queue =
            Volley.newRequestQueue(context)
        val url = baseurl + "GetFlowers.php"
        val flowers: MutableList<Flower> = mutableListOf()

        val jsonRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                Log.d("API", response.toString())
                try {
                    for (i in 0 until response.length()) {
                        val flowerRaw = response.getJSONObject(i)
                        flowers.add(Flower(flowerRaw))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                } finally {
                    callback.AddFlowersToList(flowers)
                }
            },
            { reportError(it) })
        queue.add(jsonRequest)
    }

    fun getBouquetById(bouquetId: Int, callback: BouquetsSync, context: Context) {
        val queue =
            Volley.newRequestQueue(context)
        val url = baseurl + "GetBouquet.php"
        val bouquetById: MutableList<Bouquet> = mutableListOf()

        val jsonBouquet = JSONObject().put("id", bouquetId)
        val requestBody = JSONArray().put(jsonBouquet)
        Log.d("JSON", requestBody.toString())

        val jsonRequest = JsonArrayRequest(Request.Method.POST, url, requestBody,
            { response ->
                Log.d("API", response.toString())
                try {
                    for (i in 0 until response.length()) {
                        val bouquetRaw = response.getJSONObject(i)
                        bouquetById.add(Bouquet(bouquetRaw))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                } finally {
                    callback.AddBouquetsToList(bouquetById)
                }
            },
            { reportError(it) })
        queue.add(jsonRequest)
    }

    fun getBouquets(callback: BouquetsSync, context: Context) {
        val queue = Volley.newRequestQueue(context)
        val url = baseurl + "GetBouquets.php"
        val bouquets: MutableList<Bouquet> = mutableListOf()

        val jsonRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                Log.d("API", response.toString())
                try {
                    for (i in 0 until response.length()) {
                        val bouquetRaw = response.getJSONObject(i)
                        bouquets.add(Bouquet(bouquetRaw))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                } finally {
                    callback.AddBouquetsToList(bouquets)
                }
            },
            { reportError(it) })
        queue.add(jsonRequest)
    }

    fun getBouquetsFromOrder(callback: OrderBouquetsSync, context: Context, id: Int) {
        val queue = Volley.newRequestQueue(context)
        val url = baseurl + "GetBouquetsFromOrder.php"
        val orderBouquets: MutableList<OrderBouquet> = mutableListOf()

        val jsonOrderId = JSONObject().put("id", id)
        val requestBody = JSONArray().put(jsonOrderId)
        Log.d("JSON", requestBody.toString())

        val jsonRequest = JsonArrayRequest(Request.Method.POST, url, requestBody,
            { response ->
                Log.d("API", response.toString())
                try {
                    for (i in 0 until response.length()) {
                        val bouquetRaw = response.getJSONObject(i)
                        orderBouquets.add(OrderBouquet(bouquetRaw))
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                } finally {
                    callback.showOrderItems(orderBouquets)

                }
            },
            { reportError(it) })
        queue.add(jsonRequest)
    }

    private fun reportError(error: VolleyError) {
        Log.d("API", "Something went wrong while establishing connection to server")
        Log.e("Volly Error", error.toString());


    }
}