package com.example.shenyonghe.kotlinstudy.test

import android.widget.TextView
import com.example.shenyonghe.kotlinstudy.R
import com.example.shenyonghe.kotlinstudy.util.BaseAdapter
import com.example.shenyonghe.kotlinstudy.util.BaseViewHolder

/**
 * create by shenyonghe on 2018/10/18
 */
class MyAdapter(datas: MutableList<String>) : BaseAdapter<String>(map, datas) {

    override fun conver(holder: BaseViewHolder, t: String) {
        holder.getView<TextView>(R.id.tv_title).text = t!!
    }

    companion object {
        val map = HashMap<Int, Int>()

        init {
            map.put(0, R.layout.item_test_list)
            map.put(1, R.layout.item_test2_list)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) 0 else 1
    }
}