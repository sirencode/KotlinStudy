package com.example.shenyonghe.kotlinstudy.util

import android.util.Log

/**
 * create by shenyonghe on 2018/10/15
 */
object LogUtil {
    var isCanLog: Boolean = false
    @JvmStatic
    fun LogA(tag: String, msg: String) {
        log(LogType.LogA, tag, msg)
    }

    @JvmStatic
    fun LogD(tag: String, msg: String) {
        log(LogType.LogD, tag, msg)
    }

    @JvmStatic
    fun LogE(tag: String, msg: String) {
        log(LogType.LogE, tag, msg)
    }

    @JvmStatic
    fun LogI(tag: String, msg: String) {
        log(LogType.LogI, tag, msg)
    }

    @JvmStatic
    fun LogW(tag: String, msg: String) {
        log(LogType.LogW, tag, msg)
    }

    @JvmStatic
    fun SystemO(msg: String) {
        log(LogType.SystemO, null, msg)
    }

    private fun log(type: LogType, tag: String?, msg: String) {
        if (LogUtil.isCanLog) {
            when (type) {
                LogType.LogA -> Log.wtf(tag, msg)
                LogType.LogD -> Log.d(tag, msg)
                LogType.LogE -> Log.e(tag, msg)
                LogType.LogI -> Log.i(tag, msg)
                LogType.LogV -> Log.v(tag, msg)
                LogType.LogW -> Log.w(tag, msg)
                LogType.SystemO -> System.out.println(msg)
                else -> {
                }
            }
        }
    }


    private enum class LogType {
        LogD,
        LogE,
        LogI,
        LogV,
        LogA,
        LogW,
        SystemO
    }
}