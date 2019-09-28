package jp.gr.java_conf.tenpokei.myshoppinglist.fragment

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jp.gr.java_conf.tenpokei.myshoppinglist.R

/**
 * A placeholder fragment containing a simple view.
 */
class SingleActivityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_single, container, false)
    }
}
