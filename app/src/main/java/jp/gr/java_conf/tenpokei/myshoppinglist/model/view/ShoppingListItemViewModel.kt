package jp.gr.java_conf.tenpokei.myshoppinglist.model.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import jp.gr.java_conf.tenpokei.myshoppinglist.model.data.ItemModel
import jp.gr.java_conf.tenpokei.myshoppinglist.model.repository.ItemsRepository

class ShoppingListItemViewModel(val context: Application) : AndroidViewModel(context) {
    var list = mutableListOf<MutableLiveData<ItemModel>>()
    init {
        list = ItemsRepository().selectAll()
    }

    /**
     * delete item
     */
    fun doneClick(index: Int) {
        list[index].value?.let { value ->
            ItemsRepository().updateDoneById(value.id, value.done)
        }
    }

    fun deleteItemClick(index: Int) {
        list[index].value?.let { value ->
            ItemsRepository().deleteById(value.id)
        }
        list.removeAt(index)
    }
}

