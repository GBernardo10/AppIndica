package com.br.indica.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.dimissError() {
    this.error = null
    this.isErrorEnabled = false
}