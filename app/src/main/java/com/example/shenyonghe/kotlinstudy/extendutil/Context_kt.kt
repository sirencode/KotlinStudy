package com.example.shenyonghe.kotlinstudy.extendutil

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.shenyonghe.kotlinstudy.util.ToastUtil

/**
 * Created by shenyonghe on 2017/12/27.
 */

fun Context.toast(message: String) = ToastUtil.showToast(this, message)

fun <T : Activity> Context.startActivityNewTask(clazz: Class<T>) {
    var intent = Intent(this, clazz)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    this.startActivity(intent);
}
