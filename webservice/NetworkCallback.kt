package com.pg.rxjava.webservice

interface NetworkCallback {
    fun onSuccess(serviceName: String, responseObject: Any)

    fun onFail(serviceName: String, throwable: Throwable)
}