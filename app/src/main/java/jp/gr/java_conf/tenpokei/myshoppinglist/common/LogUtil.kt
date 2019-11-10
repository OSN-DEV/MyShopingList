package jp.gr.java_conf.tenpokei.myshoppinglist.common

import android.util.Log

private const val tag = "###"

class LogUtil {


    companion object {
        fun debug(log: String) {
            val caller = Thread.currentThread().stackTrace[3]
            Log.d(tag, "[${caller.fileName}:${caller.methodName}]${log}")
        }
    }
}