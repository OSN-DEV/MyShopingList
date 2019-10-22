package jp.gr.java_conf.tenpokei.myshoppinglist.view.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import jp.gr.java_conf.tenpokei.myshoppinglist.R
import jp.gr.java_conf.tenpokei.myshoppinglist.common.LogUtil
import jp.gr.java_conf.tenpokei.myshoppinglist.common.SingleActivity
import jp.gr.java_conf.tenpokei.myshoppinglist.event.ImportClickEvent
import jp.gr.java_conf.tenpokei.myshoppinglist.event.LicenseClickEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        _toggle = object : ActionBarDrawerToggle(
            this, navigationMenu, toolbar,
            R.string.navigation_menu_open,
            R.string.navigation_menu_close
        ) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                isDrawerIndicatorEnabled = true
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }
        }


        _toggle.setToolbarNavigationClickListener { v: View? ->
            Log.d("##","##")
        }

        var navigationMenuFr =
            NavigationMenu.newInstance()
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.navigationManuContainer, navigationMenuFr)

        var shoppingList =
            ShoppingListFragment.newInstance()
        transition.replace(R.id.container, shoppingList)

        transition.commit()


        navigationMenu.addDrawerListener(_toggle)
        _toggle.syncState()

        fab.setOnClickListener { view ->


//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            val transition = supportFragmentManager.beginTransaction()
//        var shoppingList = ShoppingListFragment.newInstance()
            var shoppingList =
                ShoppingItemEditFragment.newInstance(
                    ShoppingItemEditFragment.IdNew
                )
            transition.replace(R.id.container, shoppingList)
            transition.addToBackStack(null)

            transition.commit()

            _toggle.isDrawerIndicatorEnabled = false
            _toggle.toolbarNavigationClickListener = View.OnClickListener {
                supportFragmentManager.popBackStack()
            }
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            supportActionBar?.setHomeButtonEnabled(true)

//            _toggle.syncState()


        }
    }



    override fun onBackPressed() {

//        super.onBackPressed()
//
//        if (0 < supportFragmentManager.backStackEntryCount) {
//            return
//        }
//        this.finish()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        return true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
        return when(item.itemId) {
            android.R.id.home -> {
                Log.d("##","xxx")
                return true
            }
            android.R.id.accessibilityActionContextClick -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    // *****************************************************************************************************************************
    private lateinit var _toggle: ActionBarDrawerToggle

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        _toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        _toggle.syncState()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onPause() {
        super.onPause()
        EventBus.getDefault().unregister(this)
    }

    /**
     * import menu click event from navigation menu
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @SuppressWarnings("unused")
    fun onMessageEvent(args: ImportClickEvent) {
        LogUtil.debug("ImportClickEvent received")
        this.closeMenu()
    }

    /**
     * license menu click event from navigation menu
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    @SuppressWarnings("unused")
    fun onMessageEvent(args: LicenseClickEvent) {
        LogUtil.debug("LicenseClickEvent received")
        this.closeMenu()

        val intent = SingleActivity.createIntent(this, SingleActivity.ScreenType.License)
        startActivity(intent)
    }

    /**
     * close navigation drawer
     */
    private fun closeMenu() {
        navigationMenu.closeDrawer(Gravity.LEFT)
        _toggle.syncState()
    }
}
