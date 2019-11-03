package jp.gr.java_conf.tenpokei.myshoppinglist.view.main

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.common.LogUtil
import jp.gr.java_conf.tenpokei.myshoppinglist.common.SingleActivity
import jp.gr.java_conf.tenpokei.myshoppinglist.event.FinishFragment
import jp.gr.java_conf.tenpokei.myshoppinglist.event.ImportClickEvent
import jp.gr.java_conf.tenpokei.myshoppinglist.event.LicenseClickEvent
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


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
    fun onMessageEvent(args: FinishFragment) {
        LogUtil.debug("enter")
        LogUtil.debug("FinishFragmentEvent received")
        supportFragmentManager.popBackStack()
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
        _toggle = object : ActionBarDrawerToggle(
            this, navigationMenu, toolbar,
            R.string.navigation_menu_open,
            R.string.navigation_menu_close
        ) {
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

        transition.commit()
    }

    /**
     * show item edit fragment
     */
    private fun showShoppingItemEdit(id: Long = ShoppingItemEditFragment.IdNew) {
        val transition = supportFragmentManager.beginTransaction()
        val shoppingList = ShoppingItemEditFragment.newInstance(id)
        transition.replace(R.id.container, shoppingList)
        transition.addToBackStack(null)

        transition.commit()

        _toggle.isDrawerIndicatorEnabled = false
        _toggle.toolbarNavigationClickListener = View.OnClickListener {
            LogUtil.debug("enter")
            supportFragmentManager.popBackStack()
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            _toggle.isDrawerIndicatorEnabled = true
            _toggle.syncState()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
