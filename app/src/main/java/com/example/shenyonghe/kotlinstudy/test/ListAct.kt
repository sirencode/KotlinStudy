package com.example.shenyonghe.kotlinstudy.test

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.shenyonghe.kotlinstudy.R
import kotlinx.android.synthetic.main.activity_list.*

/**
 * create by shenyonghe on 2018/10/18
 */
class ListAct : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initRecyclerView()
    }

    fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        var adapter = MyAdapter(getData())
        recyclerView.adapter = adapter
    }

    fun getData():MutableList<String>{
        var list = mutableListOf<String>()
        for (i in 1..20){
            list.add("item $i")
        }
        return list
    }
}