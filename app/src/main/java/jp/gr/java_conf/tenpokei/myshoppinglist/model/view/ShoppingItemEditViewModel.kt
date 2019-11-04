package jp.gr.java_conf.tenpokei.myshoppinglist.model.view

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jp.gr.java_conf.tenpokei.myshoppinglist.model.data.ItemModel
import jp.gr.java_conf.tenpokei.myshoppinglist.model.repository.ItemsRepository

/**
 * ViewModel for ShoppingItemEditFragment
 */
class ShoppingItemEditViewModel(val context: Application) : AndroidViewModel(context) {
    var id: Long = 0L
    var done: Boolean = false
    var shoppingItem = ObservableField<String>("")
    var memo: MutableLiveData<String?> = MutableLiveData()
    var isEdit: Boolean = false

    private fun setName(name: String) {
        this.shoppingItem.set(name)
    }

    private fun setMemo(memo: String?) {
        this.memo.value = memo
    }

    /**
     * get shopping item
     */
    fun getItem(id: Long) {
        var data = ItemsRepository().selectById(id)
        this.setName(data.shoppingItem)
        this.setMemo(data.memo)
    }

    /**
     * update shopping item
     */
    fun update() {
        var item = ItemModel(
            id = this.id,
            done = this.done,
            shoppingItem = this.shoppingItem.get()!!,
            memo = this.memo.value
        )

        if (this.isEdit) {
            ItemsRepository().updateById(item)
        } else {
            val id = ItemsRepository().insert(item)
            this.id = id
        }
    }


    class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ShoppingItemEditViewModel(application) as T
        }
    }
}
