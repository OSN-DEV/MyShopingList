package jp.gr.java_conf.tenpokei.myshoppinglist.common

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.gr.java_conf.tenpokei.myshoppinglist.R

abstract class BaseFragment : Fragment() {

    /**
     * get fragment title
     */
    abstract fun getTitle(context: Context) : String
}