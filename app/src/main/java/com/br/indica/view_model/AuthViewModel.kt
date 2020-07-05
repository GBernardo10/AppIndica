package com.br.indica.view_model

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.br.indica.R
import com.br.indica.constants.Constants
import com.br.indica.domain.model.Credential
import com.br.indica.service.user_service.UserService

class AuthViewModel : ViewModel() {

    sealed class AuthState {
        class InvalidAuth(val fields: List<Pair<String, Int>>) : AuthState()
        object ValidAuth : AuthState()
        object Unauthenticated : AuthState()
    }

    val authStateEvent = MutableLiveData<AuthState>()

    init {
        authStateEvent.value = AuthState.Unauthenticated
    }

    private fun authValid(credential: Credential) {
        if (isValidForm(credential)) {
            authStateEvent.value = AuthState.ValidAuth
        }
    }

    companion object {
        val INPUT_USERNAME = Constants.INPUT_USERNAME to R.string.invalid_username
        val INPUT_PASSWORD = Constants.INPUT_PASSWORD to R.string.invalid_password
    }

    private fun isValidForm(credential: Credential): Boolean {
        val invalidFields = arrayListOf<Pair<String, Int>>()

        if (credential.username?.value?.isEmpty() != false)
            invalidFields.add(INPUT_USERNAME)
        if (credential.password?.value?.isEmpty() != false)
            invalidFields.add(INPUT_PASSWORD)

        if (invalidFields.isNotEmpty()) {
            authStateEvent.value = AuthState.InvalidAuth(invalidFields)
            return false
        }
        return true
    }

    private val token = ObservableField<String>()
    private lateinit var service: UserService
    private lateinit var credential: Credential

    private var _username: MutableLiveData<String> = MutableLiveData()
    private var _password: MutableLiveData<String> = MutableLiveData()

    var username: MutableLiveData<String>
        set(value) {
            _username.value = value.toString()
        }
        get() = _username


    var password: MutableLiveData<String>
        set(value) {
            _password.value = value.toString()
        }
        get() = _password


    fun auth() {
        credential = Credential(username, password)
        authValid(credential)
        /*return with(RetrofitConfig.retrofit.create(UserService::class.java)) {
            auth(credential).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when (it.code()) {
                        204 -> {
                            Log.d("STATUS", it.body().toString())
                        }
                        200 -> {
                            Log.d("STATUS", it.body().toString())
                        }
                    }
                }, { err ->
                    Log.d("Err", err.message.toString())
                })
        }*/
    }
}