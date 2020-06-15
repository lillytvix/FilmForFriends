
/*https://www.themoviedb.org/settings/api Api фильмов*/
/*https://jsoneditoronline.org/#left=local.giquje json*/

package com.film.filmforfriends

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                //Тул бар

                val toolbar = findViewById<Toolbar>(R.id.toolbar_my)


                toolbar.setOnClickListener {
                    Toast.makeText(this, "Зачем вы нажали?", Toast.LENGTH_SHORT).show()
                }
                setSupportActionBar(toolbar)


                supportFragmentManager.beginTransaction()
                    .replace(R.id.Main_Activiti, FilmsGeneres.newFragment(null))
                    .commit()

                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)



                menu.setNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.Main-> show(FilmsGeneres.newFragment(null))
                        R.id.Films-> show(Films_Choice())
                    }

                    drawlerLayout.closeDrawer(GravityCompat.START) // закрываем меню
                    return@setNavigationItemSelectedListener true


                }}




     var backpressedTime: Long = 0
     var backToast: Toast? = null


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if (backpressedTime + 2000 > System.currentTimeMillis()) {
            backToast?.cancel()
            super.onBackPressed()
            return
        }else{
            backToast = Toast.makeText(baseContext,"Нажми ещё раз чтобы выйти из приложения", Toast.LENGTH_LONG)
            backToast?.show()
        }
       backpressedTime = System.currentTimeMillis()
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            if(drawlerLayout.isDrawerOpen(GravityCompat.START)){
                drawlerLayout.closeDrawer(GravityCompat.START)
            }else{
                drawlerLayout.openDrawer(GravityCompat.START)
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun show(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack("History")
            .replace(R.id.Main_Activiti, fragment)
            .commit()

    }


}
