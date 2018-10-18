package com.bbonglish.bb.api.model

import com.google.gson.annotations.SerializedName

data class LoginRes(
        @SerializedName("status_code")
        var status_code: Int? = null,
        @SerializedName("message")
        var message: String? = null,
        @SerializedName("result")
        var result: BaseRes? = null
)