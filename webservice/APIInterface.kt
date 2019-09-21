package com.pg.mvvmrxjava.webservice

import com.pg.rxjava.webservice.response.SymptomResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface APIInterface {
    @GET("session/symptom")
    fun getSymptom(): Observable<SymptomResponse>
}
