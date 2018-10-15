package com.example.shenyonghe.kotlinstudy

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.shenyonghe.kotlinstudy.util.UIUtil

/**
 * Created by shenyonghe on 2017/12/27.
 */
class BaseDialog(context: Context) : Dialog(context), View.OnClickListener {

    private var titleView: TextView? = null
    private var contentView: TextView? = null
    private var singleBtn: TextView? = null
    private var leftBtn: TextView? = null
    private var rightBtn: TextView? = null

    private var title: String? = null
    private var content: String? = null
    private var single: String? = null
    private var left: String? = null
    private var right: String? = null
    private var click: DialogClick? = null

    fun setClick(click: DialogClick) {
        this.click = click
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_base)

        setCanceledOnTouchOutside(false)
        val dialogWindow = this.window
        if (dialogWindow != null) {
            val layoutParams = window!!.attributes
            val proportion = 0.8
            layoutParams.width = (UIUtil.getWidth(context) * proportion).toInt()
            dialogWindow.setGravity(Gravity.CENTER)
            dialogWindow.attributes = layoutParams
            dialogWindow.setBackgroundDrawableResource(R.drawable.bg_base_dialog)
            initView()
        }
    }

    private fun initView() {
        titleView = findViewById(R.id.tv_title)
        contentView = findViewById(R.id.tv_content)
        singleBtn = findViewById(R.id.tv_single)
        leftBtn = findViewById(R.id.tv_left)
        rightBtn = findViewById(R.id.tv_right)
        refreshView()
        singleBtn!!.setOnClickListener(this)
        leftBtn!!.setOnClickListener(this)
        rightBtn!!.setOnClickListener(this)
    }

    fun setTitle(title: String) {
        this.title = title
        refreshView()
    }

    fun setSingle(singleText: String) {
        this.single = singleText
        refreshView()
    }

    fun setTwo(left: String, right: String) {
        this.left = left
        this.right = right
        refreshView()
    }

    fun setContent(content: String) {
        this.content = content
        refreshView()
    }

    private fun refreshView() {
        if (titleView != null) {
            if (!TextUtils.isEmpty(title)) {
                titleView!!.text = title
            }

            if (!TextUtils.isEmpty(content)) {
                contentView!!.text = content
            }

            if (!TextUtils.isEmpty(content)) {
                contentView!!.text = content
            }

            if (!TextUtils.isEmpty(single)) {
                singleBtn!!.visibility = View.VISIBLE
                singleBtn!!.text = single
            } else {
                singleBtn!!.visibility = View.GONE
            }

            if (!TextUtils.isEmpty(left) && !TextUtils.isEmpty(right)) {
                leftBtn!!.visibility = View.VISIBLE
                rightBtn!!.visibility = View.VISIBLE
                leftBtn!!.text = left
                rightBtn!!.text = right
            } else {
                leftBtn!!.visibility = View.GONE
                rightBtn!!.visibility = View.GONE
            }
        }
    }

    override fun onClick(v: View) {
        if (click != null) {
            val i = v.id
            dismiss()
            if (i == R.id.tv_single) {
                click!!.onSingleClick()
            }

            if (i == R.id.tv_left) {
                click!!.onLeftClick()
            }

            if (i == R.id.tv_right) {
                click!!.onRightClick()
            }
        }
    }

    interface DialogClick {
        fun onSingleClick()

        fun onLeftClick()

        fun onRightClick()
    }
}