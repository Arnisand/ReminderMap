package com.arnisand.remindermap.utils.extension

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(@StringRes idString: Int) {
    Toast.makeText(this, idString, Toast.LENGTH_SHORT).show()
}