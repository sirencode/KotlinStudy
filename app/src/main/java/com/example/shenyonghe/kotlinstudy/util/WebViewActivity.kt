package com.example.shenyonghe.kotlinstudy.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.shenyonghe.kotlinstudy.R
import com.example.shenyonghe.kotlinstudy.R.id.webView
import kotlinx.android.synthetic.main.acrivity_web.*

/**
 * create by shenyonghe on 2018/10/15
 */
class WebViewActivity : Activity() {
    companion object {
        private val key = "url_key"

        fun start(url: String, activity: Activity) {
            var intent = Intent(activity,WebViewActivity::class.java)
            intent.putExtra(key,url)
            activity.startActivity(intent)
        }
    }
    var url: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acrivity_web)
        initWeb()
    }

    fun initWeb(){
        if (intent != null) {
            url = intent.getStringExtra(key)
        }
        WebviewBuild.buildWebView(webView)
        webView.loadUrl(url)
    }

    override fun onDestroy() {
        WebviewBuild.releaseWebView(webView)
        super.onDestroy()
    }
}