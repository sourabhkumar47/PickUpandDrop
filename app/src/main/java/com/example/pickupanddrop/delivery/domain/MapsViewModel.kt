package com.example.pickupanddrop.delivery.domain

import androidx.lifecycle.ViewModel
import com.example.pickupanddrop.delivery.Data.LocationData
import kotlinx.coroutines.flow.MutableStateFlow

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

    // drop location added status
    private var _isDropLocationAdded = MutableStateFlow(false)
    val isDropLocationAdded get() = _isDropLocationAdded

    // location details
    var _dropLocationData = MutableStateFlow(LocationData())
    val dropLocationData get() = _dropLocationData

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

    // update drop location status
    fun updateDropLocationStatus(status: Boolean){
        _isDropLocationAdded.value = status
    }

    // update drop location data
    fun updateDropLocationData(name: String, phoneNo: String){
        _dropLocationData.value.locationType = "DROP LOCATION"
        _dropLocationData.value.franchiseLocation = _dropLocationName.value
        _dropLocationData.value.ownerName = name
        _dropLocationData.value.ownerPhoneNo = phoneNo
        _dropLocationData.value.otpFor = "receiver"
    }
}