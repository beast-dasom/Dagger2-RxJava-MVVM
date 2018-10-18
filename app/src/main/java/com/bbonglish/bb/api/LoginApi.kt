package com.bbonglish.bb.api

import com.bbonglish.bb.api.model.EditRes
import com.bbonglish.bb.api.model.LoginRes
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("/v1/auth/email")
    fun login(
            @Field("user_email") email: String,
            @Field("user_password") password: String
    ): Observable<LoginRes>
}