package jp.gr.java_conf.tenpokei.myshoppinglist.view.main


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.common.BaseFragment
import jp.gr.java_conf.tenpokei.myshoppinglist.databinding.FragmentShoppingItemEditBinding
import jp.gr.java_conf.tenpokei.myshoppinglist.model.repository.ShoppingItemsRepository
import jp.gr.java_conf.tenpokei.myshoppinglist.model.view.ShoppingItemEditViewModel
import org.koin.android.ext.android.inject

private const val KeyId = "ShoppingItemEditFragment.KeyId"

/**
 * shopping item edit fragment
 */
class ShoppingItemEditFragment : BaseFragment() {

    private var _menu: Menu? = null
    private lateinit var _binding : FragmentShoppingItemEditBinding
    private lateinit var _viewModel: ShoppingItemEditViewModel

    override fun getTitle(context: Context): String {
        return context.getString(R.string.title_shopping_item_edit)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        this._binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_item_edit, container, false)
        return this._binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this._viewModel = ViewModelProviders.of(activity!!).get(ShoppingItemEditViewModel::class.java)
        this._binding.apply {
            model = _viewModel
            lifecycleOwner = activity
        }
        if (arguments?.getLong(KeyId) != IdNew) {
            val repo : ShoppingItemsRepository by inject()
            var data = repo.getShoppingItemById(arguments!!.getLong(KeyId))
            _viewModel.item.postValue(data.item)
            _viewModel.memo.postValue(data.memo)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        if (IdNew == arguments?.getInt(
                KeyId
            ) ?: IdNew
        ) {
            inflater.inflate(R.menu.menu_edit_item_new, menu)
        } else {
            inflater.inflate(R.menu.menu_edit_item_edit, menu)
        }
        this._menu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_save_item -> {
                return true
            }
            else -> return true
        }
    }


    /**
     * save item
     */
    private fun saveItem() {

    }


    companion object {
        const val IdNew = -1L
        /**
         * create instance
         *
         * @param id id. if -1 then new record
         * @return new instance
         */
        @JvmStatic
        fun newInstance() =
            newInstance(
                IdNew
            )

        fun newInstance(id: Long) =
            ShoppingItemEditFragment().apply {
                arguments = Bundle().apply {
                    putLong(KeyId, id)
                }
            }
    }

}
