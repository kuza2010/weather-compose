package ru.adanil.weather.util.ext

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri

val Context.connectivityManager: ConnectivityManager
    get() {
        return getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

fun Context.openMap(lat: Double, lon: Double) {
    val mapIntent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("geo:$lat, $lon")
    )
    mapIntent.resolveActivity(packageManager)?.let {
        startActivity(mapIntent)
    }
}
