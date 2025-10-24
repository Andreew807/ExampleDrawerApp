package com.inforcap.exampledrawerapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var toolbar: Toolbar? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private var drawerLayout: DrawerLayout? = null
    private var fragmentManager: FragmentManager? = null
    private var navigationView: NavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar!!.setTitle("Drawer App")
        setSupportActionBar(toolbar)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        drawerLayout!!.addDrawerListener(toggle)
        toggle.syncState()

        navigationView = findViewById<NavigationView>(R.id.navigation_drawer)
        navigationView!!.setNavigationItemSelectedListener(this)

        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView!!.setOnItemSelectedListener(object :
            NavigationBarView.OnItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val itemId = item.itemId
                if (itemId == R.id.bottom_home) {
                    openFragment(HomeFragment())
                    return true
                } else if (itemId == R.id.bottom_profile) {
                    openFragment(ProfileFragment())
                    return true
                } else if (itemId == R.id.bottom_settings) {
                    openFragment(SettingFragment())
                    return true
                }
                return false
            }
        })

        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

        // 🔹 AGREGAR ESTA LÍNEA (carga los usuarios guardados)
        UserRepository.loadUsers(this)
    }


    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
        fragmentTransaction.replace(R.id.frame_container, fragment)
        fragmentTransaction.commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        when (itemId) {
            R.id.nav_home -> {
                openFragment(HomeFragment())
            }
            R.id.nav_profile -> {
                openFragment(ProfileFragment())
            }
            R.id.nav_settings -> {
                openFragment(SettingFragment())
            }
            R.id.nav_cars -> {
                openFragment(CarsFragment())
            }
            // 🔹 NUEVO BLOQUE: abre la lista de usuarios
            R.id.nav_users -> {
                openFragment(UserListFragment())
            }
            R.id.nav_library -> {
                val intent = Intent(applicationContext, LibraryActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_logout -> {
                Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
                finishAndRemoveTask()
                onDestroy()
            }
        }

        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }
}