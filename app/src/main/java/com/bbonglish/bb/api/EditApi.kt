package com.bbonglish.bb.api

import com.bbonglish.bb.api.model.EditRes
import io.reactivex.Observable
import retrofit2.http.GET

interface EditApi {

    @GET("/v1/test/players")
    fun players(): Observable<EditRes>
}