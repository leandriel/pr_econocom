package com.leandroid.system.pr_econocom.core.data

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.location.FusedLocationProviderClient

class LocationProvider(private val context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    fun getLastLocation(onSuccess: (Location) -> Unit, onFailure: (String) -> Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        onSuccess(location)
                    } else {
                        onFailure("No se pudo obtener la última ubicación.")
                    }
                }
                .addOnFailureListener { e ->
                    onFailure("Error al obtener la última ubicación: ${e.message}")
                }
        } else {
            onFailure("Permiso de ubicación denegado.")
        }
    }
}
