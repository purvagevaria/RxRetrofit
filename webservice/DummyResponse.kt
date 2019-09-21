package com.pg.rxjava.webservice.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DummyResponse {

    @Expose
    @SerializedName("Message")
    var Message: String? = null
    @Expose
    @SerializedName("ResponseCode")
    var ResponseCode: Int = 0
    @Expose
    @SerializedName("StatusIsSuccessful")
    var StatusIsSuccessful: Boolean = false
    @Expose
    @SerializedName("Data")
    var DataObject: Data? = null

    class Data {
        @Expose
        @SerializedName("SymptomList")
        var SymptomList: ArrayList<SymptomList>? = null
    }

    class SymptomList {
        @Expose
        @SerializedName("ImageURL")
        var ImageURL: String? = null
        @Expose
        @SerializedName("Name")
        var Name: String? = null
        @Expose
        @SerializedName("Id")
        var Id: Int = 0
    }
}
