package com.example.shenyonghe.kotlinstudy.util

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.KeyEvent
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

/**
 * Created bg shenyonghe on 2018/6/5.
 */
class BaseWebViewClient : WebViewClient() {

    //截取url请求，在当前视图加载，避免在跳转到自带浏览器
    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        return true
    }

    //重写此方法可以让webView处理https请求
    override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
        handler.proceed()
    }

    //重写此方法才能够处理在浏览器中的按键事件
    override fun shouldOverrideKeyEvent(view: WebView, event: KeyEvent): Boolean {
        return super.shouldOverrideKeyEvent(view, event)
    }

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        LogUtil.LogD(TAG, "onPageStarted:$url")
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        LogUtil.LogD(TAG, "onPageFinished:$url")
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
        super.onReceivedError(view, request, error)
        LogUtil.LogE(TAG, "onReceivedError:" + error.description.toString())
    }

    override fun onLoadResource(view: WebView, url: String) {
        super.onLoadResource(view, url)
        LogUtil.LogE(TAG, "onLoadResource:$url")
    }

    companion object {

        private val TAG = "BaseWebViewClient"
    }
}
