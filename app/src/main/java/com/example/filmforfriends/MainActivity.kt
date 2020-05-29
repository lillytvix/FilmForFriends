
/*https://www.themoviedb.org/settings/api Api фильмов*/
/*https://jsoneditoronline.org/#left=local.giquje json*/

package com.example.filmforfriends

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.image_item.*
import android.content.DialogInterface
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.Toast


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
            when (item.itemId) {
                R.id.Main-> show(DrammaFragment.newFragment(null))
                R.id.Films-> show(Films_Choice())

            }
            drawlerLayout.closeDrawer(GravityCompat.START) // закрываем меню
            return@setNavigationItemSelectedListener true
        }}

     var backpressedTime: Long = 0
     var backToast: Toast? = null

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
