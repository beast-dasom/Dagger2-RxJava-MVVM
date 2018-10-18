package com.bbonglish.bb.di.ui

import com.bbonglish.bb.api.SentItemsApi
import com.bbonglish.bb.ui.sentItems.SentItemsAdapter
import com.bbonglish.bb.ui.sentItems.SentItemsFragment
import com.bbonglish.bb.ui.sentItems.SentItemsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class SentItemsModule {

//    @Provides
//    fun provideAdapter(activity: SentItemsActivity): SentItemsAdapter
//            = SentItemsAdapter().apply { setItemClickListener(activity) }

    @Provides
    fun provideAdapter(fragment: SentItemsFragment): SentItemsAdapter
            = SentItemsAdapter().apply { setItemClickListener(fragment) }

    @Provides
    fun provideViewModelFactory(sentItemsApi: SentItemsApi): SentItemsViewModelFactory
            = SentItemsViewModelFactory(sentItemsApi)

}