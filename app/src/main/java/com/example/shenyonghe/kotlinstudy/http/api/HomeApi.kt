package com.example.shenyonghe.kotlinstudy.http.api

import com.example.shenyonghe.kotlinstudy.dto.DataDemo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * create by shenyonghe on 2018/10/18
 */
interface HomeApi {
    @GET("query")
    abstract fun getTest(@Query("type") type: String, @Query("postid") postid: String): Observable<DataDemo>
}