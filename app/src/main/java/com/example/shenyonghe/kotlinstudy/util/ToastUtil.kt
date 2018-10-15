package com.example.shenyonghe.kotlinstudy.util

import android.content.Context
import android.os.Build
import android.os.Handler
import android.widget.Toast
import java.lang.reflect.Field

/**
 * create by shenyonghe on 2018/10/12
 */
object ToastUtil {
    /**
     * 之前显示的内容
     */
    private var oldMsg: String? = null
    /**
     * Toast对象
     */
    private var toast: Toast? = null
    /**
     * 第一次时间
     */
    private var oneTime: Long = 0
    /**
     * 第二次时间
     */
    private var twoTime: Long = 0

    private var sField_TN: Field? = null
    private var sField_TN_Handler: Field? = null

    init {
        try {
            sField_TN = Toast::class.java!!.getDeclaredField("mTN")
            sField_TN!!.isAccessible = true
            sField_TN_Handler = sField_TN!!.type.getDeclaredField("mHandler")
            sField_TN_Handler!!.isAccessible = true
        } catch (e: Exception) {
        }
    }

    /**
     * 解决在7.1.2下toast漰溃的问题，参考文档https://tech.meituan.com/toast_snackbar_replace.html
     */
    fun hook(toast: Toast) {
        try {
            val tn = sField_TN!!.get(toast)
            val preHandler = sField_TN_Handler!!.get(tn) as Handler
            sField_TN_Handler!!.set(tn, SafelyHandlerWrapper(preHandler))
        } catch (e: Exception) {
        }

    }

    /**
     * 显示Toast
     *
     * @param context
     * @param message
     */
    @JvmStatic
    fun showToast(context: Context, message: String) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O_MR1) {
                hook(toast!!)
            }
            toast!!.show()
            oneTime = System.currentTimeMillis()
        } else {
            twoTime = System.currentTimeMillis()
            if (message == oldMsg) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast!!.show()
                }
            } else {
                oldMsg = message
                toast!!.setText(message)
                toast!!.show()
            }
        }
        oneTime = twoTime
    }
}