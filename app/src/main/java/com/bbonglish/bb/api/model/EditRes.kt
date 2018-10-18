package com.bbonglish.bb.api.model

import com.google.gson.annotations.SerializedName

class EditRes(
        @SerializedName("totalCount") val totalCount: Int,
        val result: List<EditRepo>
)
