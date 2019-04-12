package com.arnisand.remindermap.presentation.main.map

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class EventClusterItem(
    val id: Long,
    var _position: LatLng,
    var _title: String = "",
    var _snippet: String = ""
) : ClusterItem {

    override fun getSnippet(): String = _snippet

    override fun getTitle(): String = _title

    override fun getPosition(): LatLng = _position

}
