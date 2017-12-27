package com.example.shenyonghe.kotlinstudy.extendutil

import android.app.Activity
import android.content.Intent

fun <T : Activity> Activity.startActivity(clazz: Class<T>) {
    var intent: Intent = Intent(this, clazz);
    this.startActivity(intent);
}


