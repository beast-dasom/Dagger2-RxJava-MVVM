package com.bbonglish.bb.di

import android.content.Context
import com.bbonglish.bb.data.AuthTokenProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class LocalDataModule {

    // SharedPreferences

    @Provides
    @Singleton
    fun provideAuthTokenProvider(@Named("appContext") context: Context): AuthTokenProvider
            = AuthTokenProvider(context)

}
