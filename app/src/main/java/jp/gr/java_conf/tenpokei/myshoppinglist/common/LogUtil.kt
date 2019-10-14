package jp.gr.java_conf.tenpokei.myshoppinglist.common

import android.util.Log

private const val tag = "###"

class LogUtil {


    companion object {
        fun debug(log: String) {
            Log.d(tag, log)
        }
    }
}