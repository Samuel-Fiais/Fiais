package com.samuelfiais.fiais

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.samuelfiais.fiais.molecular_mass.MolecularMass
import com.samuelfiais.fiais.second_function.SecondFunction
import com.samuelfiais.molecular_mass.R
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun updateTime() {
            val data = Calendar.getInstance().time
            var dateTimeFormat = SimpleDateFormat("HH", Locale.getDefault())
            val hour = dateTimeFormat.format(data).toString().toInt()
            var greeting = if (hour in 5..11) "Good Morning" else if (hour in 12..17) "Good Afternoon" else if (hour in 18..23) "Good Evening" else "Good Night"
            welcome.text = "$greeting"
        }

        updateTime()

        val drawerLayout: DrawerLayout = drawer_layout
        val navView: NavigationView = nav_view

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {

            when(it.itemId) {
                R.id.menu_molecular_mass -> {
                    val intent = Intent(this, MolecularMass::class.java)
                    startActivity(intent)
                }
                R.id.menu_second_function -> {
                    val intent = Intent(this, SecondFunction::class.java)
                    startActivity(intent)
                }
            }

            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}