package jp.gr.java_conf.tenpokei.myshoppinglist.model.repository

import jp.gr.java_conf.tenpokei.myshoppinglist.MyApplication
import jp.gr.java_conf.tenpokei.myshoppinglist.model.entity.DatabaseHelper
import jp.gr.java_conf.tenpokei.myshoppinglist.model.data.ItemModel
import jp.gr.java_conf.tenpokei.myshoppinglist.model.entity.Items

/**
 * shopping_items repository
 */
class ItemsRepository {

//    suspend fun selectById(id: Long) : ItemModel  {
//        val record = DatabaseHelper.getInstance(MyApplication.appContext).selectFromShoppingItems()
//            .idEq(id).toList().first()
//        return ItemModel(record.id, record.name ?: "", record.memo)
//    }
    /**
     * select 'items' by id
     * @param id id
     */
    fun selectById(id: Long) : ItemModel  {
        val record = DatabaseHelper.getInstance(MyApplication.appContext).selectFromItems()
            .idEq(id).toList().first()
        return ItemModel(record.id, record.name ?: "", record.memo)
    }

    /**
     * select all 'items' record
     */
    fun selectAll():List<ItemModel>  {
        val record = DatabaseHelper.getInstance(MyApplication.appContext).selectFromItems()
                        .orderByIdAsc().toList()

        var result = ArrayList<ItemModel>()
        record.forEach {
            result.add(ItemModel(
                id = it.id,
                name = it.name ?: "",
                memo = it.memo
            ))
        }
        return result
    }

    /**
     * create 'items' record
     */
    fun insert(data: ItemModel) : Long {
        return DatabaseHelper.getInstance(MyApplication.appContext).insertIntoItems(
            Items().apply {
                name = data.name
                memo = data.memo
            })
    }


    /**
     * update 'items' record by id
     */
    fun updateById(data: ItemModel) {
        DatabaseHelper.getInstance(MyApplication.appContext).updateItems()
            .idEq(data.id)
            .name(data.name)
            .memo(data.memo)
            .modifiedAt(System.currentTimeMillis())
            .execute()
    }

    /**
     * delete 'items' by id
     */
    fun deleteById(id:Long) {
        DatabaseHelper.getInstance(MyApplication.appContext).deleteFromItems()
            .idEq(id)
            .execute()
    }
}