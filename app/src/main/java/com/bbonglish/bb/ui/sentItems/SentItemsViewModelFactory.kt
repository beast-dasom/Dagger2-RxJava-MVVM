package com.bbonglish.bb.ui.sentItems

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bbonglish.bb.api.SentItemsApi

class SentItemsViewModelFactory(val api: SentItemsApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SentItemsViewModel(api) as T
    }
}