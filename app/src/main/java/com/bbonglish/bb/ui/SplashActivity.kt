package com.bbonglish.bb.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import com.bbonglish.bb.ui.base.BaseActivity
import com.bbonglish.bb.ui.edit.EditActivity
import com.bbonglish.bb.ui.login.LoginActivity

class SplashActivity : BaseActivity() {
    private val handler = Handler()
    private var networkInfo: NetworkInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        networkInfo = manager.activeNetworkInfo
    }

    override fun onStart() {
        super.onStart()

        handler.postDelayed({

            val networkStatus: Boolean = networkInfo?.isConnectedOrConnecting ?: false
            if (networkStatus) {
                startActivity(Intent(this, EditActivity::class.java))
                finish()
            } else {
                finish()
            }
        }, 1000)
    }

//    override fun onStart() {
//        super.onStart()
//
//        handler.postDelayed({
//
//            val networkStatus: Boolean = networkInfo?.isConnectedOrConnecting ?: false
//            if (networkStatus) {
//                val restClient : LoginService = RetrofitManager.getRetrofitService(LoginService::class.java)
//                val token = restClient.token()
//                val self = this
//                token.enqueue(object : Callback<BaseRes> {
//                    override fun onFailure(call: Call<BaseRes>?, t: Throwable?) {
//                        alertNetworkError()
//                    }
//
//                    override fun onResponse(call: Call<BaseRes>?, response: Response<BaseRes>?) {
//                        if (response!!.isSuccessful()) {
//                            println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
//                            println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
//                            startActivity(Intent(self, MainActivity::class.java))
//                            finish()
//                        } else {
//                            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
//                            println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
//                            startActivity(Intent(self, LoginActivity::class.java))
//                            finish()
//                        }
//                    }
//                })
//
//            } else {
//                errorMessage("네트워크 연결이되지 않아 종료합니다.")
//                finish()
//            }
//        }, 1000)
//    }
}