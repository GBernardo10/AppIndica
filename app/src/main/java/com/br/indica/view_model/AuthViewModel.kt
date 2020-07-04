package com.br.indica.view_model

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.indica.domain.model.Credential
import com.br.indica.service.user_service.UserService

class AuthViewModel : ViewModel() {
    private val token = ObservableField<String>()
    private lateinit var service: UserService
    private lateinit var credential: Credential
    private var _username: MutableLiveData<String> = MutableLiveData()
    private var _password: MutableLiveData<String> = MutableLiveData()

    var username: String?
        set(value) {
            _username.value = value
        }
        get() = _username.value

    var password: String?
        set(value) {
            _password.value = value
        }
        get() = _password.value


    fun auth() {
        credential = Credential(username, password)

        Log.d("credential", credential.username ?: "")
        Log.d("credential", credential.password ?: "")
        /*  return with(service) {
              auth(credential).observeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread()).subscribe({ t: Token? ->
                      token.set(t?.toString())
                  }, { t: Throwable? ->
                      token.set(t?.message ?: "err")
                  })
          }*/
    }
}