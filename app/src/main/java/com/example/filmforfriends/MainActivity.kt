
/*https://www.themoviedb.org/settings/api Api фильмов*/
/*https://jsoneditoronline.org/#left=local.giquje json*/

package com.example.filmforfriends

import android.app.ProgressDialog.show
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.image_item.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        supportFragmentManager.beginTransaction()
            .replace(R.id.Main_Activiti, Films_Choice.newFragment())
            .commit()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        menu.setNavigationItemSelectedListener { item ->
            when (item.itemId) { // item - пункт меню, на который нажали
                R.id.Main-> show(DrammaFragment.newFragment(null))
                R.id.Films-> show(Films_Choice()) // если нажали на Игры, то показываем фрагмент с играми

            }
            drawlerLayout.closeDrawer(GravityCompat.START) // закрываем меню
            return@setNavigationItemSelectedListener true
        }}



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
            .replace(R.id.Main_Activiti, fragment)
            .commit()

    }


}
