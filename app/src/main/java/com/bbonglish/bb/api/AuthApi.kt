package com.bbonglish.bb.api

import com.bbonglish.bb.api.model.LoginRes
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {

//    @POST("/v1/users/")
//    @FormUrlEncoded
//    fun addUser(@Field("user_email") user_email: String,
//                @Field("user_type") user_type: String,
//                @Field("user_name") user_name: String,
//                @Field("user_phone_no") user_phone_no: String,
//                @Field("user_gender") user_gender: String,
//                @Field("user_bday") user_bday: String,
//                @Field("user_country_code") user_country_code: String,
//                @Field("user_password") user_password: String,
//                @Field("is_recv_notify") is_recv_notify: String): Call<BaseRes>
//
//    @POST("/v1/auth/email")
//    @FormUrlEncoded
//    fun login(@Field("user_email") user_email: String,
//              @Field("user_password") user_password: String): Call<LoginRes>

//    @GET("/v1/auth/token")
//    fun token(): Call<BaseRes>
}