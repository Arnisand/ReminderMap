package com.arnisand.remindermap.presentation.event.create

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.arnisand.remindermap.R
import com.arnisand.remindermap.domain.model.entiti.event.EventMap
import com.arnisand.remindermap.domain.model.values.Coordinate
import com.arnisand.remindermap.presentation.base.BaseDialogFragment
import com.arnisand.remindermap.utils.DatePatterns
import com.arnisand.remindermap.utils.extension.showToast
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.dialog_create_event.*
import java.text.SimpleDateFormat
import java.util.*

// todo save state
class CreateEventDialog : BaseDialogFragment() {

    private lateinit var viewModel: CreateEventViewModel

    private lateinit var latLng: LatLng

    private var selectDate: Date? = null
    private var selectTimeFrom: Date? = null
    private var selectTimeTo: Date? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_create_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CreateEventViewModel::class.java)
        initViewModelObserves(viewModel)

        arguments?.getParcelable<LatLng>(KEY_LOCATION)?.let {
            latLng = it
        } ?: dismissAllowingStateLoss()

        btn_create.setOnClickListener {
            if (checkFieldsValid()) {
                viewModel.saveEventMap(collectEventMap())
            } else {
                it.context.showToast(getString(R.string.dialog_create_event_fail_validation))
            }
        }

        et_date.isFocusable = false
        et_time_from.isFocusable = false
        et_time_to.isFocusable = false
        et_date.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectDate?.time ?: Date().time
            DatePickerDialog(
                it.context, android.R.style.ThemeOverlay_Material_Dark,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    val selectedCalendar = Calendar.getInstance().apply {
                        set(year, month, dayOfMonth)
                    }
                    setDate(selectedCalendar.time)
                },
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        et_time_from.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectTimeFrom?.time ?: Date().time
            TimePickerDialog(
                it.context,
                { _, hours, minutes ->
                    val selectedCalendar = Calendar.getInstance().apply {
                        set(0, 0, 0, hours, minutes)
                    }
                    setTimeFrom(selectedCalendar.time)
                },
                calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true
            ).show()
        }

        et_time_to.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = selectTimeTo?.time ?: Date().time
            TimePickerDialog(
                it.context,
                { _, hours, minutes ->
                    val selectedCalendar = Calendar.getInstance().apply {
                        set(0, 0, 0, hours, minutes)
                    }
                    setTimeTo(selectedCalendar.time)
                },
                calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true
            ).show()
        }
    }

    private fun setDate(date: Date) {
        selectDate = date
        et_date.setText(SimpleDateFormat(DatePatterns.DATE, Locale.getDefault()).format(date))
    }

    private fun setTimeFrom(date: Date) {
        selectTimeFrom = date
        et_time_from.setText(SimpleDateFormat(DatePatterns.TIME, Locale.getDefault()).format(date))
    }

    private fun setTimeTo(date: Date) {
        selectTimeTo = date
        et_time_to.setText(SimpleDateFormat(DatePatterns.TIME, Locale.getDefault()).format(date))
    }

    private fun collectEventMap(): EventMap {
        val title = et_title.text.toString()
        val description = et_description.text.toString()
        return EventMap(
            title,
            Coordinate(latLng.latitude, latLng.longitude),
            selectDate ?: Date(),
            selectTimeFrom ?: Date(),
            selectTimeTo ?: Date(),
            description
        )
    }

    private fun checkFieldsValid(): Boolean {
        if (et_title.text.isNullOrBlank() ||
            et_description.text.isNullOrBlank() ||
            selectDate == null ||
            selectTimeFrom == null ||
            selectTimeTo == null
        ) {
            return false
        }
        return true
    }

    private fun initViewModelObserves(viewModel: CreateEventViewModel) {
        viewModel.saveMarkerEvent.observe(this, androidx.lifecycle.Observer {
            targetFragment?.onActivityResult(targetRequestCode, RESULT_OK, Intent().apply {
                putExtra(KEY_ID_EVENT_MAP, it)
            })
            dismissAllowingStateLoss()
        })
    }

    companion object {
        val TAG = CreateEventDialog::class.qualifiedName

        const val RESULT_OK = 1

        const val KEY_LOCATION = "KEY_LOCATION"
        const val KEY_ID_EVENT_MAP = "KEY_ID_EVENT_MAP"

        fun newInstance(latLng: LatLng): CreateEventDialog =
            CreateEventDialog().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LOCATION, latLng)
                }
            }
    }
}