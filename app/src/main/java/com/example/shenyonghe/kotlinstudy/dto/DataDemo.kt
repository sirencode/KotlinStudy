package com.example.shenyonghe.kotlinstudy.dto

/**
 * create by shenyonghe on 2018/10/18
 */
class DataDemo {
    private var message: String? = null
    private var nu: String? = null
    private var ischeck: String? = null
    private var condition: String? = null
    private var com: String? = null

    fun getMessage(): String? {
        return message
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getNu(): String? {
        return nu
    }

    fun setNu(nu: String) {
        this.nu = nu
    }

    fun getIscheck(): String? {
        return ischeck
    }

    fun setIscheck(ischeck: String) {
        this.ischeck = ischeck
    }

    fun getCondition(): String? {
        return condition
    }

    fun setCondition(condition: String) {
        this.condition = condition
    }

    fun getCom(): String? {
        return com
    }

    fun setCom(com: String) {
        this.com = com
    }
}