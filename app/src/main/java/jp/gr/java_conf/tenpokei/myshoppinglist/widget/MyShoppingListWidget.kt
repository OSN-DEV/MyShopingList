package jp.gr.java_conf.tenpokei.myshoppinglist.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import jp.gr.java_conf.tenpokei.myshoppinglist.R

/**
 * Implementation of App Widget functionality.
 */
class MyShoppingListWidget : AppWidgetProvider() {

    // Reference
    // Android StudioでWidget作成(http://hiko00.blog85.fc2.com/blog-entry-9.html)
    // androidでwidgetを実装してみた(https://qiita.com/s-yamda/items/cba2c6c134303f29d4ab)
    //
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId
            )
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        internal fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {

            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName,
                R.layout.my_shopping_list_widget
            )
            views.setTextViewText(R.id.appwidget_text, widgetText)

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

