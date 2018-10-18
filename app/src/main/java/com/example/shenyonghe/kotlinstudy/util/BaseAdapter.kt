package com.example.shenyonghe.kotlinstudy.util

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * create by shenyonghe on 2018/10/18
 */
abstract class BaseAdapter<T>() : RecyclerView.Adapter<BaseViewHolder>() {
    var datas: MutableList<T>? = null
    var map: HashMap<Int, Int>? = null
    var itemLayId: Int = 0

    constructor(hashMap: HashMap<Int, Int>, data: MutableList<T>) : this() {
        datas = data
        map = hashMap
    }

    constructor(layId: Int, data: MutableList<T>) : this() {
        datas = data
        itemLayId = layId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        var layId = if (map == null) itemLayId else map!!.get(viewType)
        var view = LayoutInflater.from(parent.context).inflate(layId!!, parent, false)
        return BaseViewHolder.getHolder(view, viewType)
    }

    abstract fun conver(holder: BaseViewHolder, t: T)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        conver(holder, datas!!.get(position))
    }

    override fun getItemCount(): Int {
        return if (datas == null) 0 else datas!!.size
    }

    fun clearDatas() {
        datas!!.clear()
        notifyDataSetChanged()
    }

    fun addData(t: T) {
        this.datas!!.add(t!!)
        notifyDataSetChanged()
    }

    fun addDatas(list: MutableList<T>) {
        if (list != null && list.size > 0) {
            this.datas!!.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun setData(list: MutableList<T>) {
        this.datas!!.clear()
        this.datas = list!!
        notifyDataSetChanged()
    }
}