package com.example.thirdlib

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_third.*

/**
 * create by shenyonghe on 2018/10/17
 */
class ThirdAct : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        tv_third_title.text = "hello"
    }
}