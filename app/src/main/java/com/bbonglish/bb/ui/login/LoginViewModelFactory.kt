package com.bbonglish.bb.ui.login

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bbonglish.bb.api.LoginApi
import com.bbonglish.bb.data.AuthTokenProvider

class LoginViewModelFactory(
        val api: LoginApi,
        val authTokenProvider: AuthTokenProvider
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return LoginViewModel(api, authTokenProvider) as T
    }
}