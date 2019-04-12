package com.arnisand.remindermap.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arnisand.remindermap.presentation.view.LoadingDialog
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), HasSupportFragmentInjector {

    private var progressDialog: LoadingDialog? = null

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var actionBackPressed: (() -> Unit)? = null

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onBackPressed() {
        actionBackPressed?.invoke()
            ?: super.onBackPressed()
    }

    fun initDefaultFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(container_fragment.id, fragment)
            .commitAllowingStateLoss()
    }

    fun showLoading() {
        hideLoading()
        progressDialog?.show() ?: let {
            progressDialog = LoadingDialog(this)
            progressDialog?.show()
        }
    }

    fun hideLoading() {
        progressDialog?.takeIf { it.isShowing && !isFinishing }?.dismiss()
    }
}