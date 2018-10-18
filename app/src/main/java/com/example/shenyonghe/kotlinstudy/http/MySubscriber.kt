package com.example.shenyonghe.kotlinstudy.http

import io.reactivex.exceptions.Exceptions
import io.reactivex.observers.DisposableObserver

/**
 * create by shenyonghe on 2018/10/17
 */
abstract class MySubscriber<T> : DisposableObserver<T>() {
    override fun onNext(t: T) {
        if (isDisposed) {
            return
        }

        try {
            onSuccess(t)
        } catch (e: Throwable) {
            Exceptions.throwIfFatal(e)
            onFailed(RetrofitException.from(e))
        } finally {
            try {
                dispose()
            } catch (e1: Throwable) {
                Exceptions.throwIfFatal(e1)
                onFailed(RetrofitException.from(e1))
            }

        }
    }

    protected abstract fun onSuccess(t: T)

    override fun onComplete() {

    }

    override fun onError(e: Throwable) {
        if (isDisposed) {
            return
        }
        dispose()
        try {
            val exception: RetrofitException
            if (e is RetrofitException) {
                exception = e
            } else {
                exception = RetrofitException.from(e)
            }
            onFailed(exception)
        } catch (e1: Exception) {
            e1.printStackTrace()
        } finally {
            onComplete()
        }
    }

    protected open fun onFailed(e: RetrofitException) {

    }
}