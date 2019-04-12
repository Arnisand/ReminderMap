package com.arnisand.remindermap.presentation.view

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import com.arnisand.remindermap.R


class LoadingDialog(context: Context) : ProgressDialog(context, R.style.AppProgressDialog) {

    override fun show() {
        super.show()

        setCancelable(false)
        setContentView(R.layout.dialog_loading)
        window?.setGravity(Gravity.CENTER)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

}