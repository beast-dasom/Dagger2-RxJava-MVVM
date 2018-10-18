package com.bbonglish.bb.ui.edit

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bbonglish.bb.api.EditApi

class EditViewModelFactory(val api: EditApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return EditViewModel(api) as T
    }
}