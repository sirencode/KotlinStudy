package com.example.shenyonghe.kotlinstudy

import android.app.Activity
import android.os.Bundle
import com.example.shenyonghe.kotlinstudy.extendutil.startActivity
import com.example.shenyonghe.kotlinstudy.extendutil.toast
import com.example.shenyonghe.kotlinstudy.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var spHelper = SPHelper(this)
        val key = "key"
        btn_showtoast.setOnClickListener { toast(String2Num.getIntFromString("4", 0).toString()) }
        btn_tosecond.setOnClickListener { startActivity(SecondAct::class.java) }
        btn_showDialog.setOnClickListener { showDialog() }
        btn_saveSp.setOnClickListener { spHelper.put(key, "hello world") }
        btn_deleteSp.setOnClickListener { spHelper.remove(key) }
        btn_getSp.setOnClickListener { toast(spHelper.getValue(key, "no data") as String) }
        btn_go_web.setOnClickListener{WebViewActivity.start("https://www.baidu.com/",this)}
    }

    fun showDialog() {
        DialogBuild(this).setTitle("是否删除？")
                .setContent("的赵华鹏获得「年度摄影师奖」二等奖，此外还有 11 位中国摄影师获得了动物摄影、建筑摄影等分项奖。")
                //                .setSigle("确定")
                .setTwo("取消", "确定")
                .setClick(object : BaseDialog.DialogClick {
                    override fun onSingleClick() {
                       LogUtil.LogD("ue","s")
                    }

                    override fun onLeftClick() {

                    }

                    override fun onRightClick() {

                    }
                })
                .show()
    }
}
