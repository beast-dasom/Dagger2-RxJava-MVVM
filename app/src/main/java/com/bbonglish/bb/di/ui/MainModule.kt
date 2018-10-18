package com.bbonglish.bb.di.ui

import com.bbonglish.bb.ui.main.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideViewModelFactory(): MainViewModelFactory
            = MainViewModelFactory()

}