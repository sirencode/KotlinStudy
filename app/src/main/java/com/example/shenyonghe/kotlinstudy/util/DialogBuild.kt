package com.example.shenyonghe.kotlinstudy.util

import android.content.Context
import com.example.shenyonghe.kotlinstudy.BaseDialog

/**
 * create by shenyonghe on 2018/10/15
 */
class DialogBuild(context: Context) {
    private val dialog: BaseDialog

    init {
        this.dialog = BaseDialog(context)
    }

    fun setTitle(title: String): DialogBuild {
        this.dialog.setTitle(title)
        return this
    }

    fun setContent(content: String): DialogBuild {
        this.dialog.setContent(content)
        return this
    }

    fun setSigle(single: String): DialogBuild {
        this.dialog.setSingle(single)
        return this
    }

    fun setTwo(left: String, right: String): DialogBuild {
        this.dialog.setTwo(left, right)
        return this
    }

    fun setClick(click: BaseDialog.DialogClick): DialogBuild {
        this.dialog.setClick(click)
        return this
    }

    fun show() {
        this.dialog.show()
    }

}