package com.example.shenyonghe.kotlinstudy

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.example.shenyonghe.kotlinstudy.dto.DataDemo
import com.example.shenyonghe.kotlinstudy.extendutil.startActivity
import com.example.shenyonghe.kotlinstudy.extendutil.toast
import com.example.shenyonghe.kotlinstudy.http.ApiFactory
import com.example.shenyonghe.kotlinstudy.http.MySubscriber
import com.example.shenyonghe.kotlinstudy.http.RetrofitException
import com.example.shenyonghe.kotlinstudy.http.ServerDomainType
import com.example.shenyonghe.kotlinstudy.http.api.HomeApi
import com.example.shenyonghe.kotlinstudy.test.ListAct
import com.example.shenyonghe.kotlinstudy.util.*
import com.example.thirdlib.ThirdAct
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
        btn_go_web.setOnClickListener { WebViewActivity.start("https://www.baidu.com/", this) }
        btn_go_third.setOnClickListener { startActivity(ThirdAct::class.java) }
        btn_goList.setOnClickListener { startActivity(ListAct::class.java) }
        btn_getApi.setOnClickListener { getApi() }
        btn_getApi.findViewById<View>(R.id.btn_deleteSp)
    }

    fun showDialog() {
        DialogBuild(this).setTitle("是否删除？")
                .setContent("的赵华鹏获得「年度摄影师奖」二等奖，此外还有 11 位中国摄影师获得了动物摄影、建筑摄影等分项奖。")
                //                .setSigle("确定")
                .setTwo("取消", "确定")
                .setClick(object : BaseDialog.DialogClick {
                    override fun onSingleClick() {
                        LogUtil.LogD("ue", "s")
                    }

                    override fun onLeftClick() {

                    }

                    override fun onRightClick() {

                    }
                })
                .show()
    }

    fun getApi() {
        ApiFactory.getInstance()!!.create(ServerDomainType.Home, HomeApi::class.java)
                .getTest("yuantong", "11111111111")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : MySubscriber<DataDemo>() {
                    override fun onSuccess(s: DataDemo) {
                        ToastUtil.showToast(this@MainActivity, s.getMessage()!!)
                        LogUtil.LogD("onSuccess", s.getMessage()!!)
                    }

                    override fun onFailed(e: RetrofitException) {
                        super.onFailed(e)
                    }
                })
    }
}
