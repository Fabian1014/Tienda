package com.tienda.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.tienda.R
import com.tienda.ui.main.perfil.PerfilFragment
import com.tienda.ui.main.productos.CarritoFragment
import com.tienda.ui.main.productos.FavoritosFragment
import com.tienda.ui.main.productos.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navView = findViewById<NavigationView>(R.id.nav_view)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        cargarFragment(HomeFragment())
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> cargarFragment(HomeFragment())
                R.id.nav_Catalogo -> cargarFragment(FavoritosFragment())
                R.id.nav_carrito -> cargarFragment(CarritoFragment())
                R.id.nav_favoritos-> cargarFragment(PerfilFragment())
            }
            true
        }
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_Pedidos -> cargarFragment(HomeFragment())
                R.id.nav_notificaciones -> cargarFragment(FavoritosFragment())
                R.id.pago -> cargarFragment(CarritoFragment())
                R.id.nav_sesion -> cargarFragment(PerfilFragment())
            }
            drawerLayout.closeDrawers()
            true
            }
    }
    private fun cargarFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}