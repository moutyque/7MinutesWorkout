package small.app.a7minutesworkout.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import small.app.a7minutesworkout.dao.DateDAO
import small.app.a7minutesworkout.dao.ExerciseDAO
import small.app.a7minutesworkout.models.DateModel
import small.app.a7minutesworkout.models.ExerciseModel

@Database(entities = arrayOf(ExerciseModel::class, DateModel::class), version = 1)


abstract class AppDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDAO
    abstract fun dateDao(): DateDAO

}

@Volatile
private lateinit var INSTANCE: AppDatabase
fun getInstance(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {

            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build()
            //.createFromAsset("database/scores.db")
        }
        return INSTANCE;
    }
}
