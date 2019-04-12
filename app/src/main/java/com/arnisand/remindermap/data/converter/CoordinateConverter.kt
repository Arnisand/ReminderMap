package com.arnisand.remindermap.data.converter

import com.arnisand.remindermap.domain.model.values.Coordinate
import com.google.android.gms.maps.model.LatLng

class CoordinateConverter {

    fun toCoordinate(latLng: LatLng): Coordinate {
        return Coordinate(latLng.latitude, latLng.longitude)
    }

    fun toLatLng(coordinate: Coordinate): LatLng {
        return LatLng(coordinate.lat, coordinate.lng)
    }
}