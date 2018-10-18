package com.bbonglish.bb.api.model

import com.google.gson.annotations.SerializedName

class SentItemsRes(
        @SerializedName("totalCount") val totalCount: Int,
        val result: List<SentItemsRepo>
)
