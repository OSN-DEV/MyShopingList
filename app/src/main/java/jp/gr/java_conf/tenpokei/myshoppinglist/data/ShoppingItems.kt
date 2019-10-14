package jp.gr.java_conf.tenpokei.myshoppinglist.data

import androidx.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

/**
 * shopping items table
 */
@Table
class ShoppingItems {
    @PrimaryKey(autoincrement = true)
    var id: Long = 0

    @Column(indexed = true)
    var itemName: String? = null

    @Column
    @Nullable // allows NULL (default: NOT NULL)
    var memo: String? = null

    @Column
    var isChecked : Boolean = false

    @Column
    var createdAt : Long = 0


    @Column
    var modifiedAt : Long = 0
}