package com.example.shenyonghe.kotlinstudy.http

import okhttp3.Interceptor
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * create by shenyonghe on 2018/10/17
 */
class ApiFactory private constructor() {

    private val apis: MutableMap<String, Any>
    private var interceptors: Array<Interceptor>? = null
    private var interceptorMap: MutableMap<ServerDomainType, Array<Interceptor>>? = null
    private var converterFactory: Converter.Factory = GsonConverterFactory.create()
    private var enableLog = false

    init {
        apis = HashMap()
    }

    fun clearCache() {
        apis.clear()
    }

    fun addConverterFactory(factory: Converter.Factory) {
        converterFactory = factory
    }

    fun setInterceptors(interceptors: Array<Interceptor>) {
        this.interceptors = interceptors
    }

    fun setInterceptors(domainType: ServerDomainType, interceptors: Array<Interceptor>?) {
        if (interceptors == null || interceptors.size == 0) {
            return
        }
        if (interceptorMap == null) {
            interceptorMap = HashMap()
        }
        interceptorMap!![domainType] = interceptors
    }

    fun setEnableLog(enableLog: Boolean) {
        this.enableLog = enableLog
    }

    fun <T> create(serverDomainType: ServerDomainType, tClass: Class<T>, converterFactory: Converter.Factory): T {
        val key = getKey(serverDomainType, tClass)
        var api: T? = apis[key] as T
        if (api == null) {
            val retrofit = RetrofitBuilder()
                    .withDomain(DomainUtil.getDomain()[serverDomainType].toString())
                    .withDebug(enableLog)
                    .withInterceptors(getInterceptors(serverDomainType))
                    .withConvertFactory(converterFactory)
                    .build()

            api = retrofit.create(tClass)
            apis.put(key, api!!)
        }
        return api!!
    }

    private fun getKey(serverDomainType: ServerDomainType, tClass: Class<*>): String {
        return String.format(KEY_TEMPLATE, serverDomainType.name, tClass.simpleName)
    }

    private fun getInterceptors(serverDomainType: ServerDomainType): MutableList<Interceptor> {
        val res = ArrayList<Interceptor>()
        if (interceptors != null && interceptors!!.size != 0) {
            res.addAll(Arrays.asList(*interceptors!!))
        }
        if (interceptorMap != null) {
            val interceptors = interceptorMap!![serverDomainType]
            if (interceptors != null && interceptors.size != 0) {
                res.addAll(Arrays.asList(*interceptors))
            }
        }
        return res
    }

    fun <T> create(serverDomainType: ServerDomainType, tClass: Class<T>): T {
        return create(serverDomainType, tClass, converterFactory)
    }

    companion object {
        @Volatile
        private var instance: ApiFactory? = null

        fun getInstance(): ApiFactory? {
            if (instance == null) {
                synchronized(ApiFactory::class.java) {
                    if (instance == null) {
                        instance = ApiFactory()
                    }
                }
            }
            return instance
        }

        private val KEY_TEMPLATE = "%s_%s"
    }
}