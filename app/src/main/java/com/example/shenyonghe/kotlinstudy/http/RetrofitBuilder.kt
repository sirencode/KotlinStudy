package com.example.shenyonghe.kotlinstudy.http

import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * create by shenyonghe on 2018/10/17
 */
class RetrofitBuilder {
    private var domain: String? = null
    private var debug: Boolean = false
    private var convertFactory: Converter.Factory? = null
    private var callFactory: CallAdapter.Factory? = null
    private var connectTimeout = 30
    private var readTimeout = 20
    private var writeTimeout: Int = 0
    @Volatile
    private var interceptors: MutableList<Interceptor>? = null

    fun withDomain(domain: String): RetrofitBuilder {
        this.domain = domain
        return this
    }

    fun withDebug(debug: Boolean): RetrofitBuilder {
        this.debug = debug
        return this
    }

    /**
     * body convert可选，默认GsonConverterFactory
     *
     * @param convertFactory
     * @return
     */
    fun withConvertFactory(convertFactory: Converter.Factory): RetrofitBuilder {
        this.convertFactory = convertFactory
        return this
    }

    /**
     * CallAdapterFactory可选，默认RxJavaCallAdapterFactory
     *
     * @param callFactory
     * @return
     */
    fun withCallFactory(callFactory: CallAdapter.Factory): RetrofitBuilder {
        this.callFactory = callFactory
        return this
    }

    /**
     * 可选，默认30秒
     *
     * @param connectionTimeout
     * @return
     */
    fun withConnectionTimeout(connectionTimeout: Int): RetrofitBuilder {
        this.connectTimeout = connectionTimeout
        return this
    }

    /**
     * 可选，默认20秒
     *
     * @param readTimeout
     * @return
     */
    fun withReadTimeout(readTimeout: Int): RetrofitBuilder {
        this.readTimeout = readTimeout
        return this
    }

    fun withWriteTimeout(writeTimeout: Int): RetrofitBuilder {
        this.writeTimeout = writeTimeout
        return this
    }

    fun addInterceptor(interceptor: Interceptor): RetrofitBuilder {
        if (this.interceptors == null) {
            synchronized(RetrofitBuilder::class.java) {
                if (interceptors == null) {
                    interceptors = ArrayList()
                }
            }
        }
        interceptors!!.add(interceptor)
        return this
    }

    fun withInterceptors(interceptors: MutableList<Interceptor>): RetrofitBuilder {
        this.interceptors = interceptors
        return this
    }

    fun build(): Retrofit {
        if (convertFactory == null) {
            convertFactory = GsonConverterFactory.create()
        }
//        if (callFactory == null) {
//            callFactory = RxErrorHandlingCallAdapterFactory.createWithScheduler(Schedulers.io())
//        }

        return Retrofit.Builder()
                .baseUrl(domain!!)
                .addConverterFactory(convertFactory!!)
//                .addCallAdapterFactory(callFactory!!)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(createHttpClient())
                .build()
    }

    private fun createHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .connectTimeout(this.connectTimeout.toLong(), TimeUnit.SECONDS)
                .readTimeout(readTimeout.toLong(), TimeUnit.SECONDS)

        if (writeTimeout > 0) {
            builder.writeTimeout(writeTimeout.toLong(), TimeUnit.SECONDS)
        }

        if (interceptors != null) {
            for (interceptor in interceptors!!) {
                builder.addInterceptor(interceptor)
            }
        }

        if (debug) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }
}