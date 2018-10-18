package com.bbonglish.bb.ui.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.customtabs.CustomTabsIntent
import android.view.View
import com.bbonglish.bb.BuildConfig
import com.bbonglish.bb.R
import com.bbonglish.bb.api.model.LoginRes
import com.bbonglish.bb.data.AuthTokenProvider
import com.bbonglish.bb.extensions.plusAssign
import com.bbonglish.bb.rx.AutoClearedDisposable
import com.bbonglish.bb.ui.base.BaseActivity
import com.bbonglish.bb.ui.main.MainActivity
import com.bbonglish.bb.ui.signup.SignUpActivity2
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.jetbrains.anko.newTask
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class LoginActivity : BaseActivity() {

    internal val disposables = AutoClearedDisposable(this)

    internal val viewDisposables
            = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)

    @Inject
    lateinit var viewModelFactory: LoginViewModelFactory

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(
                this, viewModelFactory)[LoginViewModel::class.java]

        lifecycle += disposables
        lifecycle += viewDisposables

        btn_login.setOnClickListener {
            getAccessToken(user_email.text.toString(), user_password.text.toString())
        }

        viewDisposables += viewModel.accessToken
                .filter { !it.isEmpty }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { launchMainActivity() }

        viewDisposables += viewModel.message
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { message -> showError(message) }

        viewDisposables += viewModel.isLoading
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isLoading ->
                    if (isLoading) {
//                        showProgress()
                    } else {
//                        hideProgress()
                    }
                }

        disposables += viewModel.loadAccessToken()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

//        showProgress()

        val code = intent.data?.getQueryParameter("code")
                ?: throw IllegalStateException("No code exists")

//        getAccessToken(code)
    }

    private fun getAccessToken(email: String, password: String) {
        disposables += viewModel.requestAccessToken(email, password)
    }

    private fun launchMainActivity() {
        startActivity(intentFor<MainActivity>().clearTask().newTask())
    }

    private fun showError(message: String?) {
        with(tvActivitySearchMessage) {
            text = message ?: "Unexpected error."
            visibility = View.VISIBLE
        }
    }
}