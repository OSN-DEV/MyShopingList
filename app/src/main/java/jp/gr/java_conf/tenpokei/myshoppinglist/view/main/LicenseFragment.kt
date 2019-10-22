package jp.gr.java_conf.tenpokei.myshoppinglist.view.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_license.view.*
import java.io.BufferedReader
import java.io.InputStreamReader


/**
 * License
 */
class LicenseFragment : BaseFragment() {
    override fun getTitle(context: Context): String {
        return context.getString(R.string.title_license)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_license, container, false)
        this.showLicense(view.license)
        return view
    }

    /**
     * show license text from text file
     * @param license : textview for display license
     */
    private fun showLicense(license: TextView) {
        val source = resources.openRawResource(R.raw.license)
        val reader = BufferedReader(InputStreamReader(source))
        license.text = reader.use { it.readText() }
    }

    companion object {
        /**
         * create instance
         */
        @JvmStatic
        fun newInstance() = LicenseFragment()
    }
}
