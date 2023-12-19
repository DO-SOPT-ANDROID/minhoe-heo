package org.sopt.dosopttemplate.extension

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Context.hideKeyboard(view: View, isShown: Boolean = false) {
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).run {
        if (isShown) showSoftInput(view, 0)
        else hideSoftInputFromWindow(view.windowToken, 0)
    }
}