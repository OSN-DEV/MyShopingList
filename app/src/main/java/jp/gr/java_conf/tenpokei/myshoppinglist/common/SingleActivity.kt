package jp.gr.java_conf.tenpokei.myshoppinglist.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.fragment.LicenseFragment

import kotlinx.android.synthetic.main.activity_single.*
import java.security.InvalidParameterException

private const val KeyScreenType = "SingleActivity.ScreenType"

/**
 * screen which has only one fragment.
 */
class SingleActivity : AppCompatActivity() {

    /**
     * screen type. In this app, screen type is only one.
     */
    enum class ScreenType {
        License
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        val screenType = intent.getSerializableExtra(KeyScreenType)
        lateinit var fragment: BaseFragment
        when (screenType) {
            ScreenType.License -> fragment = LicenseFragment.newInstance()
            else -> throw InvalidParameterException("unknown screen type")
        }
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.container, fragment)
        transition.commit()

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = fragment.getTitle(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // supportActionBar?.title = fragment.getTitle(this)       // setSupportActionBarをコールした後にタイトルを変更する場合はこっち
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId) {
            android.R.id.home -> {
                LogUtil.debug("home button is pressed")
                this.finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        fun createIntent(context: Context, type: ScreenType) : Intent {
            var intent = Intent(context, SingleActivity::class.java)
            intent.putExtra(KeyScreenType, type)
            return intent
        }
    }
}
