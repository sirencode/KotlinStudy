package com.example.shenyonghe.kotlinstudy.util

import android.text.TextUtils
import android.view.View
import android.widget.TextView

/**
 * create by shenyonghe on 2018/10/17
 */
object ViewUtil {

    @JvmStatic
    fun setTVText(textView: TextView, text: String) {
        if (textView != null && !TextUtils.isEmpty(text)) {
            textView.text = text
        }
    }

    fun setViewVisible(view: View, visible: Boolean) {
        view!!.visibility = if (visible) View.VISIBLE else View.GONE
    }
}