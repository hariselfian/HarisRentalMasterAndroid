package com.hariselfian.harisrental

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hariselfian.harisrental.activity.MasukActivity
import com.hariselfian.harisrental.fragment.AkunFragment
import com.hariselfian.harisrental.fragment.HomeFragment
import com.hariselfian.harisrental.fragment.KeranjangFragment
import com.hariselfian.harisrental.helper.SharedPref

/**
 * Kode dibuat dengan cinta oleh Haris Elfian.
 */
class MainActivity : AppCompatActivity() {

    private val fragmentHome: Fragment = HomeFragment()
    private val fragmentKeranjang: Fragment = KeranjangFragment()
    private var fragmentAkun: Fragment = AkunFragment()
    private val fm: FragmentManager = supportFragmentManager
    private var active: Fragment = fragmentHome

    private lateinit var menu: Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    private var statusLogin = false

    private lateinit var s:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        s = SharedPref(this)

        setUpBottomNav()
    }
    fun setUpBottomNav(){
        fm.beginTransaction().add(R.id.container, fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container, fragmentKeranjang).hide(fragmentKeranjang).commit()
        fm.beginTransaction().add(R.id.container, fragmentAkun).hide(fragmentAkun).commit()

        bottomNavigationView = findViewById(R.id.nav_view)
        menu =bottomNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when(item.itemId) {
                R.id.navigation_home->{
                    callFragment(0, fragmentHome)
                }
                R.id.navigation_keranjang->{
                    callFragment(1, fragmentKeranjang)
                }
                R.id.navigation_akun->{
                    if(s.getStatusLogin()){
                        callFragment(2, fragmentAkun)
                    } else {
                        startActivity(Intent(this, MasukActivity::class.java))
                    }

                }
            }

            false
        }
    }

    fun callFragment(int: Int,fragment: Fragment){
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}