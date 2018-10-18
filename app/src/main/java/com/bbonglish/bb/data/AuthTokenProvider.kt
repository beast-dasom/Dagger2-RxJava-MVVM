package com.bbonglish.bb.data

import android.content.Context
import android.preference.PreferenceManager

class AuthTokenProvider(private val context: Context) {

    companion object {

        private const val KEY_AUTH_TOKEN = "access_token"
    }

    fun updateToken(token: String) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString(KEY_AUTH_TOKEN, token)
                .apply()
    }

    fun removeToken() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.remove(KEY_AUTH_TOKEN).apply()
    }

    val token: String?
        get() = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(KEY_AUTH_TOKEN, null)
}
