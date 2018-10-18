package com.bbonglish.bb.ui.base

import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_edit.*


open class BaseActivity : DaggerAppCompatActivity() {

    @Override
    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }



}
