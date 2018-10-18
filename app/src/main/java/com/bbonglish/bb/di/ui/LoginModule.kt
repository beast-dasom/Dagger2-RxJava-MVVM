package com.bbonglish.bb.di.ui

import com.bbonglish.bb.api.EditApi
import com.bbonglish.bb.api.LoginApi
import com.bbonglish.bb.data.AuthTokenProvider
import com.bbonglish.bb.ui.edit.EditActivity
import com.bbonglish.bb.ui.edit.EditAdapter
import com.bbonglish.bb.ui.edit.EditViewModelFactory
import com.bbonglish.bb.ui.login.LoginActivity
import com.bbonglish.bb.ui.login.LoginViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun provideViewModelFactory(api: LoginApi, authTokenProvider: AuthTokenProvider): LoginViewModelFactory
            = LoginViewModelFactory(api, authTokenProvider)

}