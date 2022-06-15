package com.ly.findprivacyruntime

import android.content.Context
import android.util.Log
import androidx.annotation.MainThread
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

object FindPrivacyUtils {

    private var mApplicationContext: Context? = null
    private val mDataFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)

    fun init(context: Context) {
        mApplicationContext = context
    }

    @MainThread
    fun insertStacktrace(method: String) {
        try {
            val stacktrace = Thread.currentThread().stackTrace.toMutableList()
            val saveUtilsIndex =
                stacktrace.indexOfFirst { it.className == "com.ly.findprivacyruntime.FindPrivacyUtils" }
            val newList = stacktrace.subList(saveUtilsIndex + 1,stacktrace.size-1)
            val sb = StringBuilder()
            sb.append("\n\n---------------------------${mDataFormat.format(Date(System.currentTimeMillis()))}---------------------------\n")
            var split = "invoke $method \n"
            newList.forEach {
                sb.append(split)
                sb.append("\t\t$it")
                split = "\n"
            }
            sb.append("\n----------------------------------------------------------------------------------------------\n")

            Log.e("FindPrivacyRuntime", sb.toString())
            val cacheFile = File(mApplicationContext!!.cacheDir, "PrivacyLog.txt")
            val fos = FileOutputStream(cacheFile, true)
            fos.use {
                it.write(sb.toString().toByteArray())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}