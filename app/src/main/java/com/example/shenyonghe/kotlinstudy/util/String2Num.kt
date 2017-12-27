package com.example.shenyonghe.kotlinstudy.util

import android.text.TextUtils

/**
 * Created by shenyonghe on 2017/12/27.
 */
object String2Num {
    fun getIntFromString(info: String, defaultValue: Int = 0): Int {
        var tmp = defaultValue;
        if (!TextUtils.isEmpty(info)){
            try {
                tmp = info.toInt();
            } catch (e: Exception) {
            }
        }
        return tmp;
    }
}