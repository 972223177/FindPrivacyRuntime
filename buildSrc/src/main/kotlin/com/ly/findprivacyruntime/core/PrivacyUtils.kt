package com.ly.findprivacyruntime.core

import com.google.gson.Gson
import org.apache.commons.io.FileUtils
import org.objectweb.asm.Type

object PrivacyUtils {

    private val gson = Gson()

    private var config: CheckConfig? = null
        get() {
            if (field == null) {
                field = try {
                    val file = FileUtils.getFile("method_check.json")
                    val json = file.readText()
                    gson.fromJson(json, CheckConfig::class.java)
                } catch (e: Exception) {
                    null
                }
            }
            return field
        }

    fun check(currClassName: String, owner: String, method: String): Pair<Boolean, String> {
        val targetMethod = config?.methods?.firstOrNull {
            it.owner == toRealClassName(owner) && it.method_name == method
        } ?: return false to ""
        val clazzName = toRealClassName(currClassName)
        return (targetMethod.excludes?.contains(clazzName) == false) to targetMethod.message
    }

    private fun toRealClassName(clazzName: String): String = Type.getType("L$clazzName;").className

}


data class CheckConfig(val methods: List<MethodConfig>?)
data class MethodConfig(
    val owner: String,
    val method_name: String,
    val message: String,
    val excludes: List<String>?
)