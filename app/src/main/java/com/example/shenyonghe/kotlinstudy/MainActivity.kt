package com.example.shenyonghe.kotlinstudy

import android.app.Activity
import android.os.Bundle
import com.example.shenyonghe.kotlinstudy.extendutil.startActivity
import com.example.shenyonghe.kotlinstudy.extendutil.toast
import com.example.shenyonghe.kotlinstudy.util.String2Num
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_showtoast.setOnClickListener { toast(String2Num.getIntFromString("12esds3",0).toString()) }
        btn_tosecond.setOnClickListener { startActivity(SecondAct::class.java) }
        btn_showDialog.setOnClickListener { showDialog() }
    }

    fun showDialog(){
        var dialog = BaseDialog(this,"Title")
        dialog.show()
    }
}
