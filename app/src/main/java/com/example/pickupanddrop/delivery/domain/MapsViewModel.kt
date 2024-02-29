package com.example.pickupanddrop.delivery.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MapsViewModel: ViewModel() {
    // latitude
    private var _lat = MutableStateFlow(22.4)
    val lat get() = _lat

    // longitude
    private var _lng = MutableStateFlow(79.4)
    val lng get() = _lng

    // location name by latitude and longitude
    private var _dropLocationName = MutableStateFlow("")
    val dropLocationName get() = _dropLocationName

    // map loading state
    private var _isMapLoaded = MutableStateFlow(false)
    val isMapLoaded get() = _isMapLoaded

    // update latitude
    fun updateLatitude(latVal: Double){
        _lat.value = latVal
    }

    // update longitude
    fun updateLongitude(lngVal: Double){
        _lng.value = lngVal
    }

    // update location name
    fun updateLocationName(name: String){
        _dropLocationName.value = name
    }
}