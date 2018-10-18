package com.bbonglish.bb.di
import com.bbonglish.bb.api.AuthApi
import com.bbonglish.bb.api.EditApi
import com.bbonglish.bb.api.LoginApi
import com.bbonglish.bb.api.SentItemsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideAuthApi(
            @Named("unauthorized") okHttpClient: OkHttpClient,
            callAdapter: CallAdapter.Factory,
            converter: Converter.Factory): AuthApi
            = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converter)
            .build()
            .create(AuthApi::class.java)

    @Provides
    @Singleton
    fun provideEditApi(
            @Named("authorized") okHttpClient: OkHttpClient,
            callAdapter: CallAdapter.Factory,
            converter: Converter.Factory): EditApi
            = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converter)
            .build()
            .create(EditApi::class.java)

    @Provides
    @Singleton
    fun provideSentItemsApi(
            @Named("authorized") okHttpClient: OkHttpClient,
            callAdapter: CallAdapter.Factory,
            converter: Converter.Factory): SentItemsApi
            = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converter)
            .build()
            .create(SentItemsApi::class.java)

    @Provides
    @Singleton
    fun provideLoginApi(
            @Named("unauthorized") okHttpClient: OkHttpClient,
            callAdapter: CallAdapter.Factory,
            converter: Converter.Factory): LoginApi
            = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000")
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converter)
            .build()
            .create(LoginApi::class.java)

    @Provides
    @Singleton
    fun provideCallAdapterFactory(): CallAdapter.Factory
            = RxJava2CallAdapterFactory.createAsync()

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory
            = GsonConverterFactory.create()
}