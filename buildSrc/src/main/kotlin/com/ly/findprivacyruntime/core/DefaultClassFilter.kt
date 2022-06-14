package com.ly.findprivacyruntime.core

object DefaultClassFilter {
    private val whiteList = listOf("android","androidx","kotlin","com.google")

    fun  filter(clazzName:String):Boolean = !whiteList.any {
        clazzName.contains(it)
    }
}