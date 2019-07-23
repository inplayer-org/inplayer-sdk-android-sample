package com.inplayer.android.uicommon

import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.widget.EditText

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            afterTextChanged(s.toString())
        }
        
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun EditText.getTrimmedText() = this.text.toString().trim()

/**
 * Iterate through each child element of a ViewGroup and change [isEnabled] state
 * */
fun ViewGroup.changeEnabledStateToChildViews(isEnabled: Boolean) {
    for (i in 0 until childCount) {
        val child = getChildAt(i)
        child.isEnabled = isEnabled
        if (child is ViewGroup) {
            child.changeEnabledStateToChildViews(isEnabled)
        }
    }
}
