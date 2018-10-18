package com.bbonglish.bb.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(private val token: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain)
            : Response = with(chain) {
        val newRequest = request().newBuilder().run {
            addHeader("access_token", token)
            build()
        }
        proceed(newRequest)
    }
}
