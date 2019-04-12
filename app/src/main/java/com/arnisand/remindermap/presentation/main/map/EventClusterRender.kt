package com.arnisand.remindermap.presentation.main.map

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.google.maps.android.clustering.Cluster

class EventClusterRender<T : ClusterItem>(
    context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<T>
) : DefaultClusterRenderer<T>(context, map, clusterManager) {

    override fun shouldRenderAsCluster(cluster: Cluster<T>): Boolean {
        return cluster.size > 1
    }
}