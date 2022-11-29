package com.example.buketomat.backgroundworkers

interface VolleyCallback {
    fun onSuccess(result : Any )       // uses Any so it can be used in every situation that requires Volley synchronization
}