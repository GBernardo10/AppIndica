package com.br.indica.service.user_service

import com.br.indica.domain.model.Credential
import com.br.indica.domain.model.response.Token
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET

interface UserService {
    @GET("/")
    fun auth(@Body credential: Credential): Flowable<Token>
}