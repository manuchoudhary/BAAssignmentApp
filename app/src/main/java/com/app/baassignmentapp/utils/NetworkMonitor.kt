package com.app.baassignmentapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.MutableLiveData
import java.lang.ref.WeakReference

class NetworkMonitor(context: Context?) : MutableLiveData<Boolean>() {

    val connectivityManager =
        context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            val networkCapability = connectivityManager.getNetworkCapabilities(network)
            val hasNetworkConnection =
                networkCapability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
            if (hasNetworkConnection) {
                postValue(true)
            }

        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        val networkCapabilities = connectivityManager.activeNetwork ?: return postValue(false)
        val activeN = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return postValue(false)
        when {
            activeN.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> postValue(true)
            activeN.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> postValue(true)
            activeN.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> postValue(true)
            else -> postValue(false)
        }
        val networkRequest = NetworkRequest
            .Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        if (connectivityManager.activeNetwork != null) {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

    companion object {
        private var instance: WeakReference<NetworkMonitor?>? = null
        fun getInstance(context: Context?) : NetworkMonitor {
            return instance?.get() ?: NetworkMonitor(context)
        }
    }
}