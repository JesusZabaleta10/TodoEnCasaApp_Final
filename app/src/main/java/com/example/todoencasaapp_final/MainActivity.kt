package com.example.todoencasaapp_final

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import kotlinx.android.synthetic.main.fragment_inicio.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        val InicioFragment = InicioFragment()
        transaction.add(R.id.frameLayout, InicioFragment).commit()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        when(item!!.itemId){
            R.id.action_settings -> {
                val perfilFragment = PerfilFragment()
                transaction.replace(R.id.frameLayout, perfilFragment).commit()
                //finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        when (item.itemId) {
            R.id.nav_inicio -> {
                val inicioFragment = InicioFragment()
                transaction.replace(R.id.frameLayout, inicioFragment).commit()
            }
            R.id.nav_categorias -> {
                val categoriasFragment = CategoriasFragment()
                transaction.replace(R.id.frameLayout, categoriasFragment).commit()
            }
            R.id.nav_historial -> {
                val historialFragment = HistorialFragment()
                transaction.replace(R.id.frameLayout, historialFragment).commit()
            }
            R.id.nav_solicitudes -> {
                val solicitudesFragment = SolicitudesFragment()
                transaction.replace(R.id.frameLayout, solicitudesFragment).commit()
            }
            R.id.nav_ofrecer_servicios -> {
                val ofrecerServiciosFragment = OfrecerServiciosFragment()
                transaction.replace(R.id.frameLayout, ofrecerServiciosFragment).commit()
            }
            R.id.nav_acerca -> {
                val acercaFragment = AcercaFragment()
                transaction.replace(R.id.frameLayout, acercaFragment).commit()
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()

        if(requestCode == 1234 && resultCode == Activity.RESULT_OK){
            val categoriasFragment = CategoriasFragment()
            transaction.replace(R.id.frameLayout, categoriasFragment).commit()
        }
    }
}
