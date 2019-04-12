package com.arnisand.remindermap.presentation.event.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arnisand.remindermap.R
import com.arnisand.remindermap.domain.model.entiti.event.EventMap
import com.arnisand.remindermap.presentation.base.BaseDialogFragment
import com.arnisand.remindermap.utils.DatePatterns
import kotlinx.android.synthetic.main.dialog_detail_event.*
import java.text.SimpleDateFormat
import java.util.*

class DetailEventDialog : BaseDialogFragment() {

    private lateinit var viewModel: DetailEventViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_detail_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailEventViewModel::class.java)
        initViewModelObserves(viewModel)

        arguments?.getLong(KEY_ID_EVENT_MAP)?.let {
            viewModel.loadEventMap(it)
        } ?: dismissAllowingStateLoss()

        btn_ok.setOnClickListener {
            dismissAllowingStateLoss()
        }

        btn_edit.setOnClickListener {
            targetFragment?.onActivityResult(targetRequestCode, RESULT_EDIT, Intent().apply {
                viewModel.markerEvent.value?.let {
                    putExtra(KEY_ID_EVENT_MAP, it.id)
                }
            })
            dismissAllowingStateLoss()
        }

        btn_delete.setOnClickListener {
            targetFragment?.onActivityResult(targetRequestCode, RESULT_DELETE, Intent().apply {
                viewModel.markerEvent.value?.let {
                    putExtra(KEY_ID_EVENT_MAP, it.id)
                }
            })
            dismissAllowingStateLoss()
        }
    }

    private fun initViewModelObserves(viewModel: DetailEventViewModel) {
        viewModel.markerEvent.observe(this, Observer {
            fillData(it)
        })
    }

    private fun fillData(eventMap: EventMap) {
        tv_title.text = eventMap.title
        tv_date.text = SimpleDateFormat(DatePatterns.DATE, Locale.getDefault()).format(eventMap.date)
        tv_time_from.text = SimpleDateFormat(DatePatterns.TIME, Locale.getDefault()).format(eventMap.timeFrom)
        tv_time_to.text = SimpleDateFormat(DatePatterns.TIME, Locale.getDefault()).format(eventMap.timeTo)
        tv_description.text = eventMap.description
    }

    companion object {
        val TAG = DetailEventDialog::class.qualifiedName

        const val RESULT_OK = 0
        const val RESULT_EDIT = 1
        const val RESULT_DELETE = 2

        const val KEY_ID_EVENT_MAP = "KEY_ID_EVENT_MAP"

        fun newInstance(id: Long): DetailEventDialog =
            DetailEventDialog().apply {
                arguments = Bundle().apply {
                    putLong(KEY_ID_EVENT_MAP, id)
                }
            }
    }
}