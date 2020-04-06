package com.wajahatkarim3.mvvm.gdglive.data.remote.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class BaseResponse {
    @SerializedName("status_message") @Expose var statusMessage: String = ""
    @SerializedName("success") @Expose var success: Boolean = true
    @SerializedName("status_code") @Expose var statusCode: Int = 200
}