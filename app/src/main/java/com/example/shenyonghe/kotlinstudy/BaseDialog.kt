package com.example.shenyonghe.kotlinstudy

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.dialog_base.*

/**
 * Created by shenyonghe on 2017/12/27.
 */
class BaseDialog : Dialog {
    var title: String = "";

    constructor(context: Context, title: String) : super(context) {
        this.title = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_base)
        tv_base_dialog_know.setOnClickListener { dismiss() }
        tv_base_dialog_tile.setText(title)
    }
}