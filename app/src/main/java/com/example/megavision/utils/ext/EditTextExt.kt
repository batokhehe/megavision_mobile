package com.example.megavision.utils.ext

import android.text.Editable
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import com.example.megavision.utils.ConstantCommon
import com.google.android.material.textfield.TextInputEditText

fun EditText.getSafeText(): String {
    return try {
        this.text.toString()
    } catch (e: Exception) {
        ConstantCommon.EMPTY_STRING
    }
}

fun TextInputEditText.getSafeText(): String {
    return try {
        this.text.toString()
    } catch (e: Exception) {
        ConstantCommon.EMPTY_STRING
    }
}

fun Editable.getSafeText(): String {
    return try {
        this.toString()
    } catch (e: Exception) {
        ConstantCommon.EMPTY_STRING
    }
}

fun EditText.resetEditText() {
    this.doAfterTextChanged { }
    this.setText("")
}

