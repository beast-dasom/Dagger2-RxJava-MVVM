package com.bbonglish.bb.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.bbonglish.bb.R
import com.bbonglish.bb.extensions.plusAssign
import com.bbonglish.bb.rx.AutoClearedDisposable
import com.bbonglish.bb.ui.base.BaseActivity
import com.bbonglish.bb.ui.edit.EditActivity
import com.bbonglish.bb.ui.sentItems.SentItemsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import javax.inject.Inject


class MainActivity : BaseActivity(),BottomNavigationView.OnNavigationItemSelectedListener {

    internal val viewDisposables
            = AutoClearedDisposable(lifecycleOwner = this, alwaysClearOnStop = false)

    @Inject lateinit var viewModelFactory: MainViewModelFactory

    lateinit var viewModel: MainViewModel

    lateinit var toolbar: ActionBar

    private val mainFragment: MainFragment by lazy {
        MainFragment()
    }

    private val sentItemsFragment: SentItemsFragment by lazy {
        SentItemsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(
                this, viewModelFactory)[MainViewModel::class.java]

        lifecycle += viewDisposables

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val navigation = navigationView
        navigation.setOnNavigationItemSelectedListener(this)

//        mainFragment.updateFragment()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                longToast("홈이예요")
                mainFragment.updateFragment()
                return true

            }
            R.id.navigation_write -> {
//                startActivity<SentItemsActivity>()
            }
            R.id.navigation_mypage -> {
                longToast("마이페이지")
                sentItemsFragment.updateFragment()
                return true
            }
            R.id.navigation_etc -> {
                longToast("기타 입니다")
                sentItemsFragment.updateFragment()
                return true
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnWrite -> {
                startActivity<EditActivity>()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun Fragment.updateFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, this)
                .commitNow()
    }

}