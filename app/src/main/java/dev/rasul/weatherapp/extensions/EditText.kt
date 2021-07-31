package dev.rasul.weatherapp.extensions

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


fun EditText.removeFocus() {
    clearFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm!!.hideSoftInputFromWindow(windowToken, 0)
}