package com.arnisand.remindermap.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arnisand.remindermap.R
import com.arnisand.remindermap.presentation.base.BaseFragment
import com.arnisand.remindermap.presentation.event.create.CreateEventDialog
import com.arnisand.remindermap.presentation.event.detail.DetailEventDialog
import com.arnisand.remindermap.presentation.main.map.MapHolder
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class MapFragment : BaseFragment(), OnMapReadyCallback {

    private lateinit var viewModel: MapViewModel
    private var mapHolder: MapHolder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MapViewModel::class.java)

        initViewModelObserves(viewModel)
    }

    private fun initViewModelObserves(viewModel: MapViewModel) {
        viewModel.allEventsMap.observe(this, Observer {
            mapHolder?.drawMarkers(it)
        })
        viewModel.createdEventMap.observe(this, Observer {
            mapHolder?.addMarker(it)
        })
        viewModel.removedEventMap.observe(this, Observer {
            mapHolder?.removeMarker(it)
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        context?.let {
            setMapHolder(MapHolder(it, googleMap))
        }
    }

    private fun setMapHolder(mapHolder: MapHolder) {
        this.mapHolder = mapHolder
        mapHolder.setOnMapLongClickListener {
            CreateEventDialog.newInstance(it).apply {
                setTargetFragment(this@MapFragment, REQUEST_CODE_CREATE_EVENT)
            }.show(fragmentManager, CreateEventDialog.TAG)
        }
        mapHolder.setOnMarkerClickListener { id ->
            DetailEventDialog.newInstance(id).apply {
                setTargetFragment(this@MapFragment, REQUEST_CODE_SELECT_EVENT)
            }.show(fragmentManager, DetailEventDialog.TAG)
        }

        viewModel.getAllEventsMap()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            REQUEST_CODE_CREATE_EVENT -> {
                if (resultCode == CreateEventDialog.RESULT_OK) {
                    viewModel.createEventMap(
                        data.getLongExtra(
                            CreateEventDialog.KEY_ID_EVENT_MAP,
                            0
                        )
                    )
                }
            }
            REQUEST_CODE_SELECT_EVENT -> {
                when (resultCode) {
                    DetailEventDialog.RESULT_OK -> {}
                    DetailEventDialog.RESULT_DELETE -> {
                        data.getLongExtra(DetailEventDialog.KEY_ID_EVENT_MAP, -1).takeIf { it >= 0 }?.let {
                            viewModel.removeEventMap(it)
                        }
                    }
                    DetailEventDialog.RESULT_EDIT -> {}
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    companion object {
        const val REQUEST_CODE_CREATE_EVENT = 123
        const val REQUEST_CODE_SELECT_EVENT = 124

    }
}