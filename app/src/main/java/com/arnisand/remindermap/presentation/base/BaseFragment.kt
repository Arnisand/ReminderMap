package com.arnisand.remindermap.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arnisand.remindermap.R
import com.arnisand.remindermap.presentation.view.LoadingDialog
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var progressDialog: LoadingDialog

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            progressDialog = LoadingDialog(it)
        }
    }

    protected fun addNewFragment(fragment: BaseFragment) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container_fragment, fragment, fragment::class.qualifiedName)
            ?.addToBackStack(fragment::class.qualifiedName)
            ?.commitAllowingStateLoss()
    }

    fun showLoading() {
        hideLoading()
        progressDialog.show()
    }

    fun hideLoading() {
        progressDialog.takeIf { it.isShowing && !isStateSaved }?.dismiss()
    }

    fun setActionBackPress(action: (() -> Unit)?) {
        (activity as? BaseActivity)?.actionBackPressed = action
    }
}