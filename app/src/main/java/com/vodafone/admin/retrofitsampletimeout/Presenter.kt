package com.vodafone.admin.retrofitsampletimeout

import android.util.Log
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * @author lpereira on 20/07/2017.
 */


class Presenter(val networkFactory: NetworkFactory = NetworkFactory()) {
    fun request() {
        networkFactory.request()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> Log.d("TAG", "The value is this $result") },
                        { error -> Log.e("TAG", "Ohoh an error ${Log.getStackTraceString(error)}")
                        })
    }
}