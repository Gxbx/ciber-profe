package com.mrclrchtr.android.example

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.mrclrchtr.android.example.preferencias.Preferencias
import com.mrclrchtr.android.example.plan.Plan
import com.mrclrchtr.android.example.recordatorio.Recordatorio
import com.mrclrchtr.android.example.biblioteca.Biblioteca
import com.mrclrchtr.android.example.inicio.Inicio

import com.mrclrchtr.android.example.util.OnFragmentInteractionListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {

    val TAG = "MainActivity"

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //agrego bloqueo de orientacion de pantalla
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        println("hola")

        setSupportActionBar(toolbar_main)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar_main, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val fragmentManager = fragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fooFragment = Recordatorio()
        fragmentTransaction.add(R.id.frame_layout_main_top, fooFragment)

        fragmentTransaction.commit()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.preferencias -> {
                fragmentManager.beginTransaction().add(R.id.frame_layout_main_top, Preferencias()).commit()
            }
            R.id.Plan -> {
                fragmentManager.beginTransaction().add(R.id.frame_layout_main_top, Plan()).commit()
            }
            R.id.Recorda -> {
                fragmentManager.beginTransaction().add(R.id.frame_layout_main_top, Recordatorio()).commit()
            }
            R.id.Biblio -> {
                fragmentManager.beginTransaction().add(R.id.frame_layout_main_top, Biblioteca()).commit()
            }
            R.id.Ini -> {
                fragmentManager.beginTransaction().add(R.id.frame_layout_main_top, Inicio()).commit()
            }


        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFragmentInteraction(TAG: String, uri: Uri) {
        when (TAG) {
            Preferencias.TAG -> Log.i(this.TAG, "onFragmentInteraction with Preferencias")
            Recordatorio.TAG -> Log.i(this.TAG, "onFragmentInteraction with Recordatorio")
            Plan.TAG -> Log.i(this.TAG, "onFragmentInteraction with Plan")
            Biblioteca.TAG -> Log.i(this.TAG, "onFragmentInteraction with Biblioteca")
            Inicio.TAG -> Log.i(this.TAG, "onFragmentInteraction with Inicio")
        }
    }
}
