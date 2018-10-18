package com.bbonglish.bb.ui.base

import android.arch.lifecycle.ViewModel
import com.bbonglish.bb.util.SupportOptional
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

open class BaseViewModel: ViewModel() {
    val accessToken: BehaviorSubject<SupportOptional<String>> = BehaviorSubject.create()

    val message: PublishSubject<String> = PublishSubject.create()

    val isLoading: BehaviorSubject<Boolean>
            = BehaviorSubject.createDefault(false)
}