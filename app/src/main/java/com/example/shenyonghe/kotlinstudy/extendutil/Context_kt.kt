package com.example.shenyonghe.kotlinstudy.extendutil

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by shenyonghe on 2017/12/27.
 */

fun Context.toast(message:CharSequence)= Toast.makeText(this,message, Toast.LENGTH_LONG).show()

fun <T : Activity> Context.startActivityNewTask(clazz: Class<T>) {
    var intent = Intent(this, clazz);
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    this.startActivity(intent);
}
