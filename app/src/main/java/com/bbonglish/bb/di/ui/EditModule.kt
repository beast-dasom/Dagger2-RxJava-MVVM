package com.bbonglish.bb.di.ui

import com.bbonglish.bb.api.EditApi
import com.bbonglish.bb.ui.edit.EditActivity
import com.bbonglish.bb.ui.edit.EditAdapter
import com.bbonglish.bb.ui.edit.EditViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class EditModule {

    @Provides
    fun provideAdapter(activity: EditActivity): EditAdapter
            = EditAdapter().apply { setItemClickListener(activity) }

    @Provides
    fun provideViewModelFactory(editApi: EditApi): EditViewModelFactory
            = EditViewModelFactory(editApi)

}