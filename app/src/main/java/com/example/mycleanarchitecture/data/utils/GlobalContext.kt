package com.example.mycleanarchitecture.data.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

object GlobalContext {
    const val BASEURL = "https://quotable.io/"

    fun isInternetAvailable(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
            return this.getNetworkCapabilities(this.activeNetwork)?.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            )?:false
        }
    }
}