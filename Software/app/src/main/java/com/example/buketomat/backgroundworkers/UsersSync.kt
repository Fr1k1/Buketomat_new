package com.example.buketomat.backgroundworkers

import com.example.buketomat.entites.User

interface UsersSync {
    fun onUsersReceived(result : MutableList<User> )      // uses Any so it can be used in every situation that requires Volley synchronization
}