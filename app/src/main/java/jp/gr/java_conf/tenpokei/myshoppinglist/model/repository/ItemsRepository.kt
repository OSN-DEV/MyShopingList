package jp.gr.java_conf.tenpokei.myshoppinglist.model.repository

import androidx.lifecycle.MutableLiveData
import jp.gr.java_conf.tenpokei.myshoppinglist.MyApplication
import jp.gr.java_conf.tenpokei.myshoppinglist.common.LogUtil
import jp.gr.java_conf.tenpokei.myshoppinglist.model.entity.DatabaseHelper
import jp.gr.java_conf.tenpokei.myshoppinglist.model.data.ItemModel
import jp.gr.java_conf.tenpokei.myshoppinglist.model.entity.Items

/**
 * shopping_items repository
 */
class ItemsRepository {

    /**
     * select 'items' by id
     * @param id id
     */
    fun selectById(id: Long) : ItemModel  {
        val record = DatabaseHelper.getInstance(MyApplication.appContext).selectFromItems()
            .idEq(id).toList().first()
        return ItemModel(record.id, record.done, record.shoppingItem,record.memo)
    }

    /**
     * select all 'items' record
     */
    fun selectAll():MutableList<MutableLiveData<ItemModel>>  {
        LogUtil.debug("selectAll() called")
        val record = DatabaseHelper.getInstance(MyApplication.appContext).selectFromItems()
            .orderByIdAsc().toList()

        val result = ArrayList<MutableLiveData<ItemModel>>()
        record.forEach {
            result.add(MutableLiveData<ItemModel>().apply {
                postValue(ItemModel(
                    id = it.id,
                    done = it.done,
                    shoppingItem = it.shoppingItem,
                    memo = it.memo))
            })
        }
        return result
    }

    /**
     * create 'items' record
     */
    fun insert(data: ItemModel) : Long {
        return DatabaseHelper.getInstance(MyApplication.appContext).insertIntoItems(
            Items().apply {
                done = data.done
                shoppingItem = data.shoppingItem
                memo = data.memo
                createdAt = System.currentTimeMillis()
                modifiedAt = System.currentTimeMillis()
            })
    }


    /**
     * update 'items' record by id
     */
    fun updateById(data: ItemModel) {
        DatabaseHelper.getInstance(MyApplication.appContext).updateItems()
            .idEq(data.id)
            .done(data.done)
            .shoppingItem(data.shoppingItem)
            .memo(data.memo)
            .modifiedAt(System.currentTimeMillis())
            .execute()
    }


    /**
     * update 'done' by id
     */
    fun updateDoneById(id:Long, done: Boolean) {
        DatabaseHelper.getInstance(MyApplication.appContext).updateItems()
            .idEq(id)
            .done(done)
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


    /**
     * delete checked 'items'
     */
    fun deleteByDone() {
        DatabaseHelper.getInstance(MyApplication.appContext).deleteFromItems()
            .doneEq(true)
            .execute()
    }
}