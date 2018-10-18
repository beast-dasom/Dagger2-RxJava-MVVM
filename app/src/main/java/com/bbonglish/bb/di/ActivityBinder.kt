package com.bbonglish.bb.di

import com.bbonglish.bb.di.ui.EditModule
import com.bbonglish.bb.di.ui.LoginModule
import com.bbonglish.bb.di.ui.MainModule
import com.bbonglish.bb.di.ui.SentItemsModule
import com.bbonglish.bb.ui.edit.EditActivity
import com.bbonglish.bb.ui.login.LoginActivity
import com.bbonglish.bb.ui.main.MainActivity
import com.bbonglish.bb.ui.sentItems.SentItemsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBinder {

    @ContributesAndroidInjector(modules = arrayOf(LoginModule::class))
    abstract fun bindLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(EditModule::class))
    abstract fun bindEditActivity(): EditActivity

//    @ContributesAndroidInjector(modules = arrayOf(SentItemsModule::class))
//    abstract fun bindSentItemsActivity(): SentItemsActivity

    @ContributesAndroidInjector(modules = arrayOf(SentItemsModule::class))
    abstract fun bindSentItemsActivity(): SentItemsFragment



}
