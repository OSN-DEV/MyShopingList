package jp.gr.java_conf.tenpokei.myshoppinglist.fragment


import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.common.BaseFragment

private const val KeyId = "ShoppingItemEditFragment.KeyId"

/**
 * shopping item edit fragment
 */
class ShoppingItemEditFragment : BaseFragment() {

    override fun getTitle(context: Context): String {
        return context.getString(R.string.title_shopping_item_edit)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_shopping_item_edit, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        if (IdNew == arguments?.getInt(KeyId) ?: IdNew) {
            inflater?.inflate(R.menu.menu_edit_item_new, menu)
        } else {
            inflater?.inflate(R.menu.menu_edit_item_edit, menu)
        }
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
        const val IdNew = -1

        /**
         * create instance
         *
         * @param id id. if -1 then new record
         * @return new instance
         */
        @JvmStatic
        fun newInstance(id: Int) =
            ShoppingItemEditFragment().apply {
                arguments = Bundle().apply {
                    putInt(KeyId, id)
                }
            }
    }

}
