package com.br.indica.domain.model

import androidx.lifecycle.MutableLiveData

data class Credential(val username: MutableLiveData<String>?, val password: MutableLiveData<String>?)