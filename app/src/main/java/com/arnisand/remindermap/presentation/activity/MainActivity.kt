package com.arnisand.remindermap.presentation.activity

import android.os.Bundle
import com.arnisand.remindermap.R
import com.arnisand.remindermap.presentation.base.BaseActivity
import com.arnisand.remindermap.presentation.main.MapFragment
import dagger.android.AndroidInjection

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: initDefaultFragment(MapFragment())
    }
}
