package small.app.a7minutesworkout.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import small.app.a7minutesworkout.Constants.Companion.exercisesList
import small.app.a7minutesworkout.R
import small.app.a7minutesworkout.database.getInstance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = getInstance(applicationContext)
        GlobalScope.launch {
            exercisesList.forEach { db.exerciseDao().insert(it) }
            db.exerciseDao().getAll().forEach {
                Log.d("MainActivity", it.toString())
            }
        }

    }


}