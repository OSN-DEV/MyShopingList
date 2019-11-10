package jp.gr.java_conf.tenpokei.myshoppinglist.view.main

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.TransitionSet
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.common.LogUtil
import jp.gr.java_conf.tenpokei.myshoppinglist.common.SingleActivity
import jp.gr.java_conf.tenpokei.myshoppinglist.event.FinishFragmentEvent
import jp.gr.java_conf.tenpokei.myshoppinglist.event.ImportClickEvent
import jp.gr.java_conf.tenpokei.myshoppinglist.event.LicenseClickEvent
import jp.gr.java_conf.tenpokei.myshoppinglist.model.data.ItemModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

// https://codeday.me/jp/qa/20181208/56445.html
// https://riptutorial.com/ja/android/example/19883/%E3%83%95%E3%83%A9%E3%82%B0%E3%83%A1%E3%83%B3%E3%83%88%E9%96%93%E3%81%AE%E9%81%B7%E7%A7%BB%E3%82%92%E3%82%A2%E3%83%8B%E3%83%A1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E5%8C%96%E3%81%99%E3%82%8B
// http://android.sakuraweb.com/wordpress/2017/04/17/fragment%E3%82%92%E3%82%A2%E3%83%8B%E3%83%A1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E3%81%95%E3%81%9B%E3%81%A6%E9%81%B7%E7%A7%BB%E3%81%99%E3%82%8B/
// https://github.com/lgvalle/Material-Animations
// https://qiita.com/verno3632/items/a97942a461204af4f421
class MainActivity : AppCompatActivity() {
    private lateinit var _toggle: ActionBarDrawerToggle

    /**
     * create activity
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.debug("enter")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setupToolbar()
        if (null == savedInstanceState) {
            this.setupScreen()
        }
        fab.setOnClickListener {
            showShoppingItemEdit()
        }
    }

    /**
     * post create event
     */
    override fun onPostCreate(savedInstanceState: Bundle?) {
        LogUtil.debug("enter")
        super.onPostCreate(savedInstanceState)
        _toggle.syncState()
    }

    /**
     * configuration changed event
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        LogUtil.debug("enter")
        super.onConfigurationChanged(newConfig)
        _toggle.syncState()
    }

    /**
     * on start event
     */
    override fun onStart() {
        LogUtil.debug("enter")
        super.onStart()
        EventBus.getDefault().register(this)
    }

    /**
     * on pause event
     */
    override fun onPause() {
        LogUtil.debug("enter")
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    /**
     * import menu click event from navigation menu
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Suppress("unused", "UNUSED_PARAMETER")
    fun onMessageEvent(args: ImportClickEvent) {
        LogUtil.debug("enter")
        LogUtil.debug("ImportClickEvent received")
        this.closeMenu()
    }

    /**
     * license menu click event from navigation menu
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Suppress("unused", "UNUSED_PARAMETER")
    fun onMessageEvent(args: LicenseClickEvent) {
        LogUtil.debug("enter")
        LogUtil.debug("LicenseClickEvent received")
        this.closeMenu()

        val intent = SingleActivity.createIntent(this, SingleActivity.ScreenType.License)
        startActivity(intent)
    }

    /**
     * Finish fragment
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Suppress("unused", "UNUSED_PARAMETER")
    fun onMessageEvent(args: FinishFragmentEvent) {
        LogUtil.debug("enter")
        supportFragmentManager.popBackStack()
        this.shoppingItemEditFinished(true)
    }

    /**
     * Finish fragment
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @Suppress("unused", "UNUSED_PARAMETER")
    fun onMessageEvent(args: ItemModel) {
        LogUtil.debug("enter")
        this.showShoppingItemEdit(args.id)
    }


    /**
     * close navigation drawer
     */
    private fun closeMenu() {
        LogUtil.debug("enter")
        navigationMenu.closeDrawer(GravityCompat.START)
        _toggle.syncState()
    }

    /**
     * set up toolbar
     */
    private fun setupToolbar() {
        // if setSupportActionBar call after create toggle instance
        // toolbarNavigationClickListener not working(call option menu selected event)
        setSupportActionBar(toolbar)
        _toggle = object : ActionBarDrawerToggle(this, navigationMenu, toolbar, R.string.navigation_menu_open,
            R.string.navigation_menu_close) {
        }

        navigationMenu.addDrawerListener(_toggle)
        _toggle.syncState()
    }

    /**
     * set up initial screen
     */
    private fun setupScreen() {
        val navigationMenuFr =
            NavigationMenu.newInstance()
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.navigationManuContainer, navigationMenuFr)

        val shoppingList =
            ShoppingListFragment.newInstance()
        transition.replace(R.id.container, shoppingList)

        supportFragmentManager.addOnBackStackChangedListener {
            LogUtil.debug("count" + supportFragmentManager.backStackEntryCount)
            if (0 == supportFragmentManager.backStackEntryCount) {
                shoppingItemEditFinished(false)
            }
        }

        transition.commit()
    }

    /**
     * show item edit fragment
     */
    private fun showShoppingItemEdit(id: Long = ShoppingItemEditFragment.IdNew) {
        val transition = supportFragmentManager.beginTransaction()
        val shoppingList = ShoppingItemEditFragment.newInstance(id)

        val ts = TransitionSet()
        ts.addTransition(Fade())
        ts.addTransition(Slide())
        shoppingList.enterTransition = ts

        transition.replace(R.id.container, shoppingList)
        transition.addToBackStack(null)

        transition.commit()
        _toggle.isDrawerIndicatorEnabled = false
        _toggle.toolbarNavigationClickListener = View.OnClickListener {
            LogUtil.debug("enter")
            shoppingItemEditFinished(true)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        fab.hide()
    }

    /**
     * close shopping item edit
     */
    private fun shoppingItemEditFinished(pop:Boolean) {
        if (pop) {
            supportFragmentManager.popBackStack()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        _toggle.isDrawerIndicatorEnabled = true
        _toggle.syncState()
        this.currentFocus?.let { v ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
        fab.show()
    }
}
