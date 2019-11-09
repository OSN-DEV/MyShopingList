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
            _callback.update(position)
        }
        list.removeAt(position)
    }
}

