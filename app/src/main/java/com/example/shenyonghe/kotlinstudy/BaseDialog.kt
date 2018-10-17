package com.example.shenyonghe.kotlinstudy

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import com.example.shenyonghe.kotlinstudy.util.UIUtil
import com.example.shenyonghe.kotlinstudy.util.ViewUtil
import kotlinx.android.synthetic.main.dialog_base.*

/**
 * Created by shenyonghe on 2017/12/27.
 */
class BaseDialog(context: Context) : Dialog(context), View.OnClickListener {

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
        refreshView()
        tv_single!!.setOnClickListener(this)
        tv_left!!.setOnClickListener(this)
        tv_right!!.setOnClickListener(this)
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
        if (tv_title != null) {
            ViewUtil.setTVText(tv_title, this!!.title!!)
            ViewUtil.setTVText(tv_content, this!!.content!!)
            if (!TextUtils.isEmpty(single)) {
                tv_single!!.visibility = View.VISIBLE
                tv_single!!.text = single
            } else {
                tv_single!!.visibility = View.GONE
            }
            if (!TextUtils.isEmpty(left) && !TextUtils.isEmpty(right)) {
                tv_left!!.visibility = View.VISIBLE
                tv_right!!.visibility = View.VISIBLE
                tv_left!!.text = left
                tv_right!!.text = right
            } else {
                tv_left!!.visibility = View.GONE
                tv_right!!.visibility = View.GONE
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