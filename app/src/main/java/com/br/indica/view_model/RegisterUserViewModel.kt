package com.br.indica.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.indica.domain.model.User

class RegisterUserViewModel : ViewModel() {
    private lateinit var user: User
    private var _username: MutableLiveData<String> = MutableLiveData()
    private var _password: MutableLiveData<String> = MutableLiveData()
    private var _mail: MutableLiveData<String> = MutableLiveData()

    var username: String?
        get() = _username.value
        set(value) {
            _username.value = value
        }

    var mail: String?
        get() = _mail.value
        set(value) {
            _mail.value = value
        }

    var password: String?
        get() = _password.value
        set(value) {
            _password.value = value
        }


    fun registerUser() {
        user = User(username, mail, password)
    }
}