package com.bbonglish.bb.api

import com.bbonglish.bb.api.model.SentItemsRes
import io.reactivex.Observable
import retrofit2.http.GET

interface SentItemsApi {

    @GET("/v1/test/players")
    fun players(): Observable<SentItemsRes>
}