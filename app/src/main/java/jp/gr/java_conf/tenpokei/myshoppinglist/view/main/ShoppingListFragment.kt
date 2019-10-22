package jp.gr.java_conf.tenpokei.myshoppinglist.view.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import kotlinx.android.synthetic.main.fragment_shopping_list_item.view.*

interface RowEventListener {
    fun onDoneCheckedChanged(position: Int)
    fun onShoppingItemClick(position: Int)
    fun onDeleteItemClick(position: Int)
}

/**
 * Shopping List
 */
class ShoppingListFragment : Fragment(),
    RowEventListener {
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
                          private val lister: RowEventListener,
                          private val itemList:List<String>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {


        private var _listener: RowEventListener
        init {
            _listener = lister
        }
        public fun setListner(listener: RowEventListener) {
            _listener = listener
        }

        class ViewHolder(view: View,listener: RowEventListener) :RecyclerView.ViewHolder(view) {

            val shoppingItemRow: LinearLayout = view.shoppingItemRow
            val done: CheckBox = view.findViewById(R.id.done);
            val shoppingItem: TextView = view.shoppingItem
            val deleteItem: ImageButton = view.deleteItem
            init {
                shoppingItemRow.setOnClickListener { _ ->
                    Log.d("####","shoppingItemRow")
                }
                done.setOnCheckedChangeListener{view, isChecked  ->
                    Log.d("####","setOnCheckedChangeListener")
                    listener.onDoneCheckedChanged(1)
                }
                shoppingItem.setOnClickListener { _ ->
                    Log.d("####","shoppingItem")
                }
                deleteItem.setOnClickListener { _ ->
                    Log.d("####","deleteItem")
                }
            }



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
            holder?.let {
                it.shoppingItem.text = itemList.get(position)


//                it.done.setOnClickListener {
//
//                    Log.d("###","")
//                }
//
//                it.done.setOnCheckedChangeListener{view, isChecked  ->
//                    Toast.makeText(context, "check", Toast.LENGTH_SHORT).show()
//                }
//                it.shoppingItem.setOnClickListener { _ ->
//                    Toast.makeText(context, "shopping item click", Toast.LENGTH_SHORT).show()
//                }
//                it.deleteItem.setOnClickListener { _ ->
//                    Toast.makeText(context, "delete item click", Toast.LENGTH_SHORT).show()
//                }
            }
        }

        override fun getItemCount(): Int {
            return itemList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.fragment_shopping_list_item, parent, false)

//            view.done.setOnCheckedChangeListener{view, isChecked  ->
//                Log.d("####","AAAAA")
//                Toast.makeText(context, "check", Toast.LENGTH_SHORT).show()
//            }
//            view.shoppingItem.setOnClickListener { _ ->
//                Log.d("####","AAAAA")
//                Toast.makeText(context, "shopping item click", Toast.LENGTH_SHORT).show()
//            }
//            view.deleteItem.setOnClickListener { _ ->
//                Log.d("####","AAAAA")
//                Toast.makeText(context, "delete item click", Toast.LENGTH_SHORT).show()
//            }
            return ViewHolder(
                view,
                _listener
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        var view = inflater.inflate(R.layout.fragment_shopping_list, container, false)

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val list:List<String> = mutableListOf("A", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C", "B", "C")
        activity?.let {
            shoppingList.layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
            shoppingList.adapter =
                RecyclerAdapter(
                    it,
                    this,
                    list
                )
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
