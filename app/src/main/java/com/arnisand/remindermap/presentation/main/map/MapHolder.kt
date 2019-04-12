package com.arnisand.remindermap.presentation.main.map

import android.content.Context
import com.arnisand.remindermap.domain.model.entiti.event.EventMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager

class MapHolder(
    context: Context,
    private val googleMap: GoogleMap
) {

    private val clusterManager: ClusterManager<EventClusterItem> = ClusterManager(context, googleMap)

    init {
        googleMap.setOnCameraIdleListener(clusterManager)
        googleMap.setOnMarkerClickListener(clusterManager)
        googleMap.setInfoWindowAdapter(clusterManager.markerManager)

        clusterManager.renderer = EventClusterRender(context, googleMap, clusterManager)

        clusterManager.setOnClusterClickListener {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it.position, googleMap.cameraPosition.zoom + 1f))
            true
        }
    }

    fun addMarker(eventMap: EventMap) {
        clusterManager.addItem(
            EventClusterItem(
                eventMap.id,
                LatLng(eventMap.coordinate.lat, eventMap.coordinate.lng),
                eventMap.title
            )
        )
        clusterManager.cluster()
    }

    fun drawMarkers(eventMapList: List<EventMap>) {
        eventMapList.forEach {
            addMarker(it)
        }
    }

    fun setOnMapLongClickListener(function: (latLng: LatLng) -> Unit) {
        googleMap.setOnMapLongClickListener {
            function.invoke(it)
        }
    }

    fun setOnMarkerClickListener(function: (id: Long) -> Unit) {
        clusterManager.setOnClusterItemClickListener { selected ->
            function(selected.id)
            true
        }
    }

    fun removeMarker(id: Long) {
        clusterManager.algorithm.items
            .firstOrNull { it?.id == id }
            ?.let {
                clusterManager.removeItem(it)
                clusterManager.cluster()
            }
    }
}