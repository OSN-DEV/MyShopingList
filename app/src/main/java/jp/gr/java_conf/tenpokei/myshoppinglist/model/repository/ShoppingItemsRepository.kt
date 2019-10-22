package jp.gr.java_conf.tenpokei.myshoppinglist.model.repository

import android.content.Context
import jp.gr.java_conf.tenpokei.myshoppinglist.MyApplication
import jp.gr.java_conf.tenpokei.myshoppinglist.model.entity.DatabaseHelper
import jp.gr.java_conf.tenpokei.myshoppinglist.model.data.ShoppingItemDataModel

/**
 * shopping_items repository
 */
class ShoppingItemsRepository {

//    suspend fun getShoppingItemById(id: Long) : ShoppingItemDataModel  {
//        val record = DatabaseHelper.getInstance(MyApplication.appContext).selectFromShoppingItems()
//            .idEq(id).toList().first()
//        return ShoppingItemDataModel(record.id, record.itemName ?: "", record.memo)
//    }
    fun getShoppingItemById(id: Long) : ShoppingItemDataModel  {
        val record = DatabaseHelper.getInstance(MyApplication.appContext).selectFromShoppingItems()
            .idEq(id).toList().first()
        return ShoppingItemDataModel(record.id, record.itemName ?: "", record.memo)
    }

    suspend fun getShoppingItemList(context: Context):List<ShoppingItemDataModel>  {
        val record = DatabaseHelper.getInstance(context).selectFromShoppingItems()
                        .orderByIdAsc().toList()

        var result = ArrayList<ShoppingItemDataModel>()
        record.forEach {
            result.add(ShoppingItemDataModel(
                id = it.id,
                item = it.itemName ?: "",
                memo = it.memo
            ))
        }
        return result
    }
}