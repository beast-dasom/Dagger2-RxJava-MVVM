package com.bbonglish.bb.ui.login

import android.arch.lifecycle.ViewModel
import com.bbonglish.bb.api.LoginApi
import com.bbonglish.bb.data.AuthTokenProvider
import com.bbonglish.bb.ui.base.BaseViewModel
import com.bbonglish.bb.util.SupportOptional
import com.bbonglish.bb.util.optionalOf
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class LoginViewModel(
        val api: LoginApi,
        val authTokenProvider: AuthTokenProvider
): BaseViewModel() {

    fun loadAccessToken(): Disposable
            = Single.fromCallable { optionalOf(authTokenProvider.token) }
            .subscribeOn(Schedulers.io())
            .subscribe(Consumer<SupportOptional<String>> {
                accessToken.onNext(it)
            })

    fun requestAccessToken(email: String, password: String): Disposable
            = api.login(email, password)
            .map { it.result!!.access_token }
            .doOnSubscribe { isLoading.onNext(true) }
            .doOnTerminate { isLoading.onNext(false) }
            .subscribe({ token ->
                authTokenProvider.updateToken(token)
                accessToken.onNext(optionalOf(token))
            }) {
                message.onNext(it.message ?: "로그인에 실패 하였습니다.")
            }

}