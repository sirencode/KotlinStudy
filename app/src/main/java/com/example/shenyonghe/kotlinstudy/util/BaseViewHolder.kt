package com.example.shenyonghe.kotlinstudy.util

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.View

/**
 * create by shenyonghe on 2018/10/18
 */
class BaseViewHolder : RecyclerView.ViewHolder {
    var views: SparseArray<View>
    var rootView: View
    var viewType: Int

    constructor(root: View, type: Int) : super(root) {
        views = SparseArray()
        rootView = root
        viewType = type
    }

    fun <T : View> getView(viewId: Int): T {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = rootView.findViewById(viewId)
            views.put(viewId, view)
        }
        return (view as T?)!!
    }

    fun setClickListener(viewId: Int, listener: View.OnClickListener): BaseViewHolder {
        var view: View = getView(viewId)
        view!!.setOnClickListener(listener)
        return this
    }

    companion object {
        fun <T : BaseViewHolder> getHolder(view: View, viewType: Int) : T {
           return BaseViewHolder(view,viewType) as T
        }
    }
}