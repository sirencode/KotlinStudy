package com.example.shenyonghe.kotlinstudy.util

import android.content.Context
import android.util.DisplayMetrics

/**
 * create by shenyonghe on 2018/10/15
 */
object UIUtil {
    var displayMetrics: DisplayMetrics? = null
    var width: Int = 0
    var height: Int = 0
    var density: Float = 0f

    private fun getDisplayMetrics(context: Context): DisplayMetrics? {
        if (displayMetrics == null) {
            displayMetrics = context.resources.displayMetrics
        }
        return displayMetrics
    }

    private fun getDensity(context: Context): Float {
        if (density == 0f) {
            density = getDisplayMetrics(context)!!.density
        }
        return density
    }

    public fun getWidth(context: Context): Int {
        if (width == 0) {
            width = getDisplayMetrics(context)!!.widthPixels
        }
        return width
    }

    public fun getHeight(context: Context): Int {
        if (height == 0) {
            height = getDisplayMetrics(context)!!.heightPixels
        }
        return height
    }

    public fun dp2px(context: Context, deValue: Int): Int {
        return (deValue * getDensity(context) + 0.5f).toInt()
    }

    public fun px2dp(context: Context, pxValue: Int): Int {
        return (pxValue * getDensity(context) + 0.5f).toInt()
    }
}