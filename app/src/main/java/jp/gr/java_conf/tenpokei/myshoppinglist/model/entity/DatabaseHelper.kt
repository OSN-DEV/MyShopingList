package jp.gr.java_conf.tenpokei.myshoppinglist.model.entity

import android.content.Context
import com.github.gfx.android.orma.AccessThreadConstraint

/**
 * データベースヘルパー
 */
object DatabaseHelper {
    // https://kotaeta.com/64921938

    @Volatile private var database: OrmaDatabase? = null

    /**
     * get database instance
     */
    fun getInstance(context: Context) : OrmaDatabase =
        database ?: synchronized(this) {
            database
                ?: build(context).also { database = it }
        }

    /**
     * build database
     */
    private fun build(context: Context) =
        OrmaDatabase.builder(context)
            .readOnMainThread(AccessThreadConstraint.NONE)
            .writeOnMainThread(AccessThreadConstraint.NONE)
            .build()
}

