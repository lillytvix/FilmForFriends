
/*https://www.themoviedb.org/settings/api Api фильмов*/
/*https://jsoneditoronline.org/#left=local.giquje json*/

package com.example.filmforfriends

import android.app.ProgressDialog.show
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.framelayout, DrammaFragment())
            .commit()
        }
    }
