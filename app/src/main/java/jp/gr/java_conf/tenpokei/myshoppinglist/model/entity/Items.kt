package jp.gr.java_conf.tenpokei.myshoppinglist.model.entity

import androidx.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

/**
 * shopping items table
 */
@Table
class Items {
    // https://qiita.com/SYABU555/items/9eeaaa2a1172b479b6c4

    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @Column(indexed = true)
    var name: String? = null

    @Column
    @Nullable // allows NULL (default: NOT NULL)
    var memo: String? = null

    @Column
    var createdAt : Long = 0

    @Column
    var modifiedAt : Long = 0

}