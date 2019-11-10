package jp.gr.java_conf.tenpokei.myshoppinglist.view.widget

import android.app.AlarmManager
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RemoteViews
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.common.LogUtil
import jp.gr.java_conf.tenpokei.myshoppinglist.databinding.FragmentShoppingListItemBinding
import jp.gr.java_conf.tenpokei.myshoppinglist.model.view.ShoppingListItemViewModel

/**
 * Widge
 */
class MyShoppingListWidget : AppWidgetProvider() {


    /**
     * Recycler adapter
     */
    class RecyclerAdapter(private val viewModel: ShoppingListItemViewModel,
                          private val parentLifecycleOwner: LifecycleOwner) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

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



    // Reference
    // Android StudioでWidget作成(http://hiko00.blog85.fc2.com/blog-entry-9.html)
    // androidでwidgetを実装してみた(https://qiita.com/s-yamda/items/cba2c6c134303f29d4ab)
    // https://developer.android.com/guide/practices/ui_guidelines/widget_design.html#anatomy_determining_size
    // https://qiita.com/rild/items/43881b44a356d9888aa1
    //
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    fun setTimer(context: Context) {
        var alarm: AlarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
        alarm.setRepeating(AlarmManager.ELAPSED_REALTIME, System.currentTimeMillis(), 2000, null)
//        alaram(AlarmManager.ELAPSED_REALTIME, 2000, )

    }


    companion object {

        internal fun updateAppWidget(
                context: Context, appWidgetManager: AppWidgetManager,
                appWidgetId: Int
        ) {

//            val widgetText = context.getString(R.string.appwidget_text)
//            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.my_shopping_list_widget)
//            views.setTextViewText(R.id.appwidget_text, widgetText)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

