package jp.gr.java_conf.tenpokei.myshoppinglist.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.gr.java_conf.tenpokei.myshoppinglist.BuildConfig
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.common.LogUtil
import jp.gr.java_conf.tenpokei.myshoppinglist.event.ImportClickEvent
import jp.gr.java_conf.tenpokei.myshoppinglist.event.LicenseClickEvent
import kotlinx.android.synthetic.main.fragment_navigation_menu.view.*
import org.greenrobot.eventbus.EventBus


/**
 * Shopping Menu Item
 */
class NavigationMenu : Fragment() {
    /**
     * create view event
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_navigation_menu, container, false)
        view.appVersion.text = BuildConfig.VERSION_NAME
        view.menuImport.setOnClickListener { this.onImportClick() }
        view.menuLicense.setOnClickListener { this.onLicenseClick() }
        return view
    }

    /**
     * import menu click
     */
    private fun onImportClick() {
        LogUtil.debug("enter")
        EventBus.getDefault().post(ImportClickEvent())
    }

    /**
     * licence menu click
     */
    private fun onLicenseClick() {
        LogUtil.debug("enter")
        EventBus.getDefault().post(LicenseClickEvent())
    }


    companion object {
        /**
         * create instance
         */
        @JvmStatic
        fun newInstance() = NavigationMenu()
    }
}
