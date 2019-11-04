package jp.gr.java_conf.tenpokei.myshoppinglist.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.common.LogUtil
import jp.gr.java_conf.tenpokei.myshoppinglist.databinding.FragmentShoppingListItemBinding
import jp.gr.java_conf.tenpokei.myshoppinglist.model.view.ShoppingListItemViewModel
import jp.gr.java_conf.tenpokei.myshoppinglist.model.view.ShoppingListItemViewModelCallback
import kotlinx.android.synthetic.main.fragment_shopping_list.*


// https://qiita.com/ta_sato_kuu/items/7e9e99b30b6ab45f5a84
// https://qiita.com/nitakan/items/01565b4b20c88cd79e64
// https://spin.atomicobject.com/2019/06/08/kotlin-recyclerview-data-binding/
// https://qiita.com/tsumuchan/items/c357b543ccb771e11621
// http://e10dokup.hateblo.jp/entry/2017/05/06/024923

/**
 * Shopping List
 */
class ShoppingListFragment : Fragment() {
    private lateinit var _viewModel: ShoppingListItemViewModel

    /**
     * Recycler adapter
     */
    class RecyclerAdapter(private val viewModel: ShoppingListItemViewModel,
                          private val parentLifecycleOwner: LifecycleOwner) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(), ShoppingListItemViewModelCallback {

        override fun update(position: Int) {
            notifyItemChanged(position)
        }

        class ViewHolder(var binding: FragmentShoppingListItemBinding) :
            RecyclerView.ViewHolder(binding.root)

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.viewModel = viewModel
            holder.binding.position = position
            holder.binding.lifecycleOwner = parentLifecycleOwner
        }

        override fun getItemCount(): Int {
            return viewModel.list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            LogUtil.debug("enter")
            val binding = DataBindingUtil.inflate<FragmentShoppingListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.fragment_shopping_list_item,
                parent,
                false
            )
            return ViewHolder(binding)
        }
    }

    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.debug("enter")
        super.onCreate(savedInstanceState)
    }

    /**
     *
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtil.debug("enter")
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    /**
     *
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        LogUtil.debug("enter")
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            _viewModel = ShoppingListItemViewModel(
                it.application,
                object : ShoppingListItemViewModelCallback {
                    override fun update(position: Int) {
                        shoppingList.adapter?.notifyItemChanged(position)
                    }
                })
            shoppingList.layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            shoppingList.adapter = RecyclerAdapter(_viewModel, it)
        }
    }

    /**
     *
     */
    companion object {
        @JvmStatic
        fun newInstance() =
            ShoppingListFragment().apply {
            }
    }
}
