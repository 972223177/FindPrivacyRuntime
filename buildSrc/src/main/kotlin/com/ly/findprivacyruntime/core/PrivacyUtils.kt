package com.ly.findprivacyruntime.core

object PrivacyUtils {
   private val test = listOf(MethodInfo("android/telephony/TelephonyManager","getImei"),
      MethodInfo("android/content/pm/PackageManager","getInstalledPackages"))

    fun contain(owner:String,method:String):Boolean = test.firstOrNull {
       it.owner == owner && it.methodName == method
    } !=null

}

data class MethodInfo(val owner:String,val methodName:String)