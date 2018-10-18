package com.bbonglish.bb.di

import android.app.Application
import com.bbonglish.bb.BbonglishApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(
                AppModule::class,
                LocalDataModule::class,
                ApiModule::class, NetworkModule::class,
                AndroidSupportInjectionModule::class, ActivityBinder::class))
interface AppComponent : AndroidInjector<BbonglishApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}
