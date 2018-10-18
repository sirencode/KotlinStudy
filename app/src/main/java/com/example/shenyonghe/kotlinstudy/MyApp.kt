package com.example.shenyonghe.kotlinstudy

import android.app.Application
import com.example.shenyonghe.kotlinstudy.util.LogUtil

/**
 * create by shenyonghe on 2018/10/18
 */
class MyApp : Application() {
    private var instance: MyApp? = null

    override fun onCreate() {
        super.onCreate()
        LogUtil.isCanLog = BuildConfig.DEBUG
        instance = this
    }

    fun getApplication(): MyApp? {
        return instance
    }
}