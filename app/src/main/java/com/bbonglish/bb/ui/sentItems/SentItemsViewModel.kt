package com.bbonglish.bb.ui.sentItems
import android.arch.lifecycle.ViewModel
import com.bbonglish.bb.api.SentItemsApi
import com.bbonglish.bb.api.model.SentItemsRepo
import com.bbonglish.bb.util.SupportOptional
import com.bbonglish.bb.util.emptyOptional
import com.bbonglish.bb.util.optionalOf
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

class SentItemsViewModel(
        val api: SentItemsApi)
    : ViewModel() {

    // 검색 결과를 전달할 서브젝트, 초기값으로 빈 값을 지정
    val searchResult: BehaviorSubject<SupportOptional<List<SentItemsRepo>>>
            = BehaviorSubject.createDefault(emptyOptional())
    // 마지막 검색어를 전달한 서브젝트 초기값 빈 값 지정
    val lastSearchKeyword: BehaviorSubject<SupportOptional<String>>
            = BehaviorSubject.createDefault(emptyOptional())
    // 화면에 표시할 메시지를 전달할 서브젝트
    val message: BehaviorSubject<SupportOptional<String>> = BehaviorSubject.create()

    // 작업 진행 상태를 전달할 서브젝트 초기값 false
    val isLoading: BehaviorSubject<Boolean>
            = BehaviorSubject.createDefault(false)

    // 검색 결과를 요
    fun searchRepository(): Disposable
            = api.players()
            // 검색어를 LastSearchKeyword 서브젝트에 전달합니다.
//            .doOnNext { lastSearchKeyword.onNext(optionalOf(query)) }
            .flatMap {
//                if (0 == it.totalCount) {
//                    Observable.error(IllegalStateException("No search result"))
//                } else {
//                    Observable.just(it.result)
//                }
                Observable.just(it.result)
            }
            // 검색을 시작히기 전에 현재 화면에 표시되고 있던 검색 결과 및 메시지를 모두 제거
            .doOnSubscribe {
                searchResult.onNext(emptyOptional())
                message.onNext(emptyOptional())
                isLoading.onNext(true)
            }
            // 작업이 종료되면 (정상 종료, 오류 모두 포함) 작업 진행 상태를 false로 바꿈.
            .doOnTerminate { isLoading.onNext(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                //  검색 결과를 searchResult 에 전달.
                searchResult.onNext(optionalOf(result))
            }) {
                message.onNext(optionalOf(it.message ?: "Unexpected error"))
            }

}