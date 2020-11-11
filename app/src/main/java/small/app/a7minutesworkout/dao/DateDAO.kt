package small.app.a7minutesworkout.dao

import androidx.room.*
import small.app.a7minutesworkout.models.DateModel

@Dao
interface DateDAO {
    @Query("SELECT * FROM DateModel")
    fun getAll(): List<DateModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg exercise: DateModel)

    @Update
    fun update(vararg exercise: DateModel)

    @Delete
    fun delete(exercise: DateModel)
}