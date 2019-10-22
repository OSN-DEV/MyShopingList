package jp.gr.java_conf.tenpokei.myshoppinglist.model.view

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.standalone.KoinComponent

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
    var id: MutableLiveData<Long> = MutableLiveData()
    var item: MutableLiveData<String> = MutableLiveData()
    var memo: MutableLiveData<String?> = MutableLiveData()

}
