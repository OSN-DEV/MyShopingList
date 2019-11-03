package jp.gr.java_conf.tenpokei.myshoppinglist.view.main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.gr.java_conf.tenpokei.myshoppinglist.MyApplication
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.common.LogUtil
import jp.gr.java_conf.tenpokei.myshoppinglist.databinding.FragmentShoppingListItemBinding
import jp.gr.java_conf.tenpokei.myshoppinglist.model.view.ShoppingListItemViewModel
import kotlinx.android.synthetic.main.fragment_shopping_list.*

interface RowEventListener {
    fun onDoneCheckedChanged(position: Int)
    fun onShoppingItemClick(position: Int)
    fun onDeleteItemClick(position: Int)
}

// https://qiita.com/ta_sato_kuu/items/7e9e99b30b6ab45f5a84
// https://qiita.com/nitakan/items/01565b4b20c88cd79e64
// https://spin.atomicobject.com/2019/06/08/kotlin-recyclerview-data-binding/

/**
 * Shopping List
 */
class ShoppingListFragment : Fragment(), RowEventListener {

    lateinit var _binding: FragmentShoppingListItemBinding
    lateinit var _viewModel : ShoppingListItemViewModel

    override fun onDoneCheckedChanged(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onShoppingItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDeleteItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class RecyclerAdapter(private val context: Context,
                          private val viewModel : ShoppingListItemViewModel,
                          private val parentLifecycleOwner:LifecycleOwner) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


//        private var _listener: RowEventListener
//        init {
//            _listener = lister
//        }
//        public fun setListner(listener: RowEventListener) {
//            _listener = listener
//        }

        class ViewHolder(var binding: FragmentShoppingListItemBinding) :RecyclerView.ViewHolder(binding.root) {
//
//            val shoppingItemRow: LinearLayout = view.shoppingItemRow
//            val done: CheckBox = view.findViewById(R.id.done);
//            val shoppingItem: TextView = view.shoppingItem
//            val deleteItem: ImageButton = view.deleteItem
//            init {
//                shoppingItemRow.setOnClickListener { _ ->
//                    Log.d("####","shoppingItemRow")
//                }
//                done.setOnCheckedChangeListener{view, isChecked  ->
//                    Log.d("####","setOnCheckedChangeListener")
//                    listener.onDoneCheckedChanged(1)
//                }
//                shoppingItem.setOnClickListener { _ ->
//                    Log.d("####","shoppingItem")
//                }
//                deleteItem.setOnClickListener { _ ->
//                    Log.d("####","deleteItem")
//                }
//            }



        }


        private var _recyclerView: RecyclerView? = null

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
            super.onAttachedToRecyclerView(recyclerView)
            this._recyclerView = recyclerView
        }

        override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
            super.onDetachedFromRecyclerView(recyclerView)
            this._recyclerView = null
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.binding.viewModel  = viewModel
            holder.binding.position = position
            holder.binding.lifecycleOwner = parentLifecycleOwner
        }

        override fun getItemCount(): Int {
            return viewModel.list.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            LogUtil.debug("enter")
            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.fragment_shopping_list_item, parent, false)

            val binding = DataBindingUtil.inflate<FragmentShoppingListItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.fragment_shopping_list_item,
                parent,
                false
            )

            return ViewHolder(binding)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.debug("enter")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        LogUtil.debug("enter")
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        LogUtil.debug("enter")
        super.onActivityCreated(savedInstanceState)
        activity?.let {
            _viewModel = ViewModelProviders.of(this).get(ShoppingListItemViewModel(it.application).javaClass)
            shoppingList.layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            shoppingList.adapter = RecyclerAdapter(it, _viewModel, it)
        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoppingListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ShoppingListFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
            }
    }
}
