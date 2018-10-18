package com.bbonglish.bb.extensions

import com.bbonglish.bb.rx.AutoClearedDisposable
import com.bbonglish.bb.rx.AutoClearedDisposableFrag
import io.reactivex.disposables.Disposable

operator fun AutoClearedDisposable.plusAssign(disposable: Disposable) = this.add(disposable)
operator fun AutoClearedDisposableFrag.plusAssign(disposable: Disposable) = this.add(disposable)