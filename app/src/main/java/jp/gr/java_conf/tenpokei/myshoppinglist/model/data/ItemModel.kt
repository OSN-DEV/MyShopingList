package jp.gr.java_conf.tenpokei.myshoppinglist.model.data

/**
 * MyShoppingItem model
 */
data class ItemModel(
    var id: Long,
    var done: Boolean,
    var shoppingItem: String,
    var memo: String?
)
