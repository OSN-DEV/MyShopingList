package jp.gr.java_conf.tenpokei.myshoppinglist.model.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import jp.gr.java_conf.tenpokei.myshoppinglist.common.LogUtil
import jp.gr.java_conf.tenpokei.myshoppinglist.model.data.ItemModel
import jp.gr.java_conf.tenpokei.myshoppinglist.model.repository.ItemsRepository
import org.greenrobot.eventbus.EventBus

interface ShoppingListItemViewModelCallback {
    fun update(position : Int)
    fun removed(position: Int)
    fun updateAll()
}

class ShoppingListItemViewModel(val context: Application, private val callback : ShoppingListItemViewModelCallback) : AndroidViewModel(context) {
    var list = mutableListOf<MutableLiveData<ItemModel>>()
    private var _callback = callback

    init {
        list = ItemsRepository().selectAll()
        LogUtil.debug("count:" + list.count())
    }

    /**
     * change done
     */
    fun doneClick(position: Int) {
        LogUtil.debug("enter")
        list[position].value?.let { value ->
            ItemsRepository().updateDoneById(value.id, value.done)
            _callback.update(position)
        }

    }

    fun itemClick (position: Int) {
        LogUtil.debug("enter")
        EventBus.getDefault().post(list[position].value)
    }

    /**
     * delete item
     */
    fun deleteItemClick(position: Int) {
        LogUtil.debug("enter")
        list[position].value?.let { value ->
            ItemsRepository().deleteById(value.id)
            list.removeAt(position)
        }
        _callback.removed(position)
    }

    /**
     * if more than one checked item, return true
     */
    fun hasDoneItems() : Boolean {
        list.forEach{ item ->
            item.value?.let {
                if (it.done) {
                    return true
                }
            }
        }
        return false
    }


    /**
     * delete checked item
     */
    fun deleteByDone() {
        ItemsRepository().deleteByDone()
        for(i in (list.size -1) downTo  0) {
            list[i].value?.let {
                if (it.done) {
                    list.removeAt(i)
                }

            }
        }
        _callback.updateAll()
    }
}

