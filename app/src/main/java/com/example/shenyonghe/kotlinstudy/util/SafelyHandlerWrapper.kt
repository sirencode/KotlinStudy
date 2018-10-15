package com.example.shenyonghe.kotlinstudy.util

import android.os.Handler
import android.os.Message

/**
 * create by shenyonghe on 2018/10/12
 */
class SafelyHandlerWrapper(private var handler: Handler) : Handler() {

    override fun dispatchMessage(msg: Message?) {
        try {
            super.dispatchMessage(msg)
        } catch (e: Exception) {
        }

    }

    override fun handleMessage(msg: Message?) {
        try {
            handler.handleMessage(msg)
        } catch (e: Exception) {
        }

    }
}