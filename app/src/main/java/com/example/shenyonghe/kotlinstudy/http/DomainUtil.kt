package com.example.shenyonghe.kotlinstudy.http

import android.os.Build.PRODUCT
import com.example.shenyonghe.kotlinstudy.BuildConfig
import com.example.shenyonghe.kotlinstudy.http.DomainUtil.TEST

/**
 * create by shenyonghe on 2018/10/17
 */
object DomainUtil {
    private val PRODUCT = object : HashMap<ServerDomainType, String>() {
        init {
            put(ServerDomainType.Home, "https://www.kuaidi100.com/")
        }
    }

    private val TEST = object : HashMap<ServerDomainType, String>() {
        init {
            put(ServerDomainType.Home, "https://www.kuaidi100.com/")
        }
    }

    fun getDomain(): Map<ServerDomainType, String> {
        return if (BuildConfig.DEBUG)
            TEST
        else
            PRODUCT
    }
}