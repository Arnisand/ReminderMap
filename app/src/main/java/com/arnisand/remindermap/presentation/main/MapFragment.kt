package com.arnisand.remindermap.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.arnisand.remindermap.R
import com.arnisand.remindermap.presentation.base.BaseFragment
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var viewModel: MapViewModel
    private lateinit var mMap: GoogleMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)

        initViewModelObserves(viewModel)

        (fragmentManager?.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync(this)
    }

    private fun initViewModelObserves(viewModel: MapViewModel) {
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }
}