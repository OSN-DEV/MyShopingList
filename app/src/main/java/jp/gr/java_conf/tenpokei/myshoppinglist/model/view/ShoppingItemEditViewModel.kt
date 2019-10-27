package jp.gr.java_conf.tenpokei.myshoppinglist.model.view

import android.app.Application
import androidx.databinding.ObservableField
import androidx.databinding.ObservableLong
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
    //    private var _id: MutableLiveData<Long> = MutableLiveData()
//    private var _item: MutableLiveData<String> = MutableLiveData()
//    private var _memo: MutableLiveData<String?> = MutableLiveData()
//
//    var id: LiveData<Long> = _id
//    var item: LiveData<String> = _item
//    var memo: LiveData<String?> = _memo
//
//    @Bindable
//    fun getItem() : String {
//        return _item.value ?: ""
//    }
//    fun setItem(item: String) {
//        _item.postValue(item)
//    }
//
//    @Bindable
//    fun getMemo() : String? {
//        return _memo.value
//    }
//    fun setMemo(memo: String?) {
//        _memo.postValue(memo)
//    }
    var id: Long = 0L
    var name = ObservableField<String>("")
    var memo: MutableLiveData<String?> = MutableLiveData()
    var isEdit: Boolean = false

    fun setName(name: String) {
        this.name.set(name)
    }

    fun setMemo(memo: String?) {
        this.memo.value = memo
    }

    /**
     * get shopping item
     */
    fun getItem(id: Long) {
        var data = ItemsRepository().selectById(id)
        this.setName(data.name)
        this.setMemo(data.memo)
    }

    /**
     * update shopping item
     */
    fun update() {
        var item = ItemModel(
            id = this.id,
            name = this.name.toString(),
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
