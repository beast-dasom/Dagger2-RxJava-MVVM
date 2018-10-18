package com.bbonglish.bb.ui.sentItems

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.*
import com.bbonglish.bb.R
import com.bbonglish.bb.api.model.SentItemsRepo
import com.bbonglish.bb.di.DaggerAppComponent
import com.bbonglish.bb.extensions.plusAssign
import com.bbonglish.bb.rx.AutoClearedDisposable
import com.bbonglish.bb.rx.AutoClearedDisposableFrag
import com.bbonglish.bb.ui.base.BaseActivity
import com.bbonglish.bb.ui.repo.RepositoryActivity
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_sent_items.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SentItemsFragment : DaggerFragment(), SentItemsAdapter.ItemClickListener{

    internal lateinit var menuSearch: MenuItem

    internal lateinit var searchView: SearchView

    internal val disposables = AutoClearedDisposableFrag(this)

    internal val viewDisposables
            = AutoClearedDisposableFrag(lifecycleOwner = this, alwaysClearOnStop = false)

    @Inject lateinit var adapter: SentItemsAdapter

    @Inject lateinit var viewModelFactory: SentItemsViewModelFactory

    lateinit var viewModel: SentItemsViewModel

    lateinit var toolbar: ActionBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_sent_items, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(
                this, viewModelFactory)[SentItemsViewModel::class.java]

        lifecycle += disposables
        lifecycle += viewDisposables



        with(rvActivitySearchList) {
            layoutManager = LinearLayoutManager(activity)
            adapter = this@SentItemsFragment.adapter
        }

        viewDisposables += viewModel.searchResult
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { items ->
                    with(adapter) {
                        if (items.isEmpty) {
                            clearItems()
                        } else {
                            setItems(items.value)
                        }
                        notifyDataSetChanged()
                    }
                }

        viewDisposables += viewModel.message
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { message ->
                    if (message.isEmpty) {
                        hideError()
                    } else {
                        showError(message.value)
                    }
                }

        viewDisposables += viewModel.isLoading
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { isLoading ->
                    if (isLoading) {
                        showProgress()
                    } else {
                        hideProgress()
                    }
                }

        searchRepository()
    }


//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_activity_search, menu)
//
//        menuSearch = menu.findItem(R.id.menu_activity_search_query)
//        searchView = (menuSearch.actionView as SearchView)
//
//        viewDisposables += searchView.queryTextChangeEvents()
//                .filter { it.isSubmitted }
//                .map { it.queryText() }
//                .filter { it.isNotEmpty() }
//                .map { it.toString() }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { query ->
//                    updateTitle(query)
//                    hideSoftKeyboard()
//                    collapseSearchView()
//                    searchRepository()
//                }
//
//
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (R.id.menu_activity_search_query == item.itemId) {
//            item.expandActionView()
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onItemClick(repository: SentItemsRepo) {
//        disposables += viewModel.addToSearchHistory(repository)
    }

    private fun searchRepository() {
        disposables += viewModel.searchRepository()
    }

    private fun showProgress() {
        pbActivitySearch.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        pbActivitySearch.visibility = View.GONE
    }

    private fun hideError() {
        with(tvActivitySearchMessage) {
            text = ""
            visibility = View.GONE
        }
    }

    private fun showError(message: String?) {
        with(tvActivitySearchMessage) {
            text = message ?: "Unexpected error."
            visibility = View.VISIBLE
        }
    }

//    private fun updateTitle(query: String) {
//        supportActionBar?.run { subtitle = query }
//    }
//
//    private fun hideSoftKeyboard() {
//        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).run {
//            hideSoftInputFromWindow(searchView.windowToken, 0)
//        }
//    }
//
//    private fun collapseSearchView() {
//        menuSearch.collapseActionView()
//    }

}