package small.app.a7minutesworkout.dao

import androidx.room.*
import small.app.a7minutesworkout.models.ExerciseModel

@Dao
interface ExerciseDAO {
    @Query("SELECT * FROM ExerciseModel")
    fun getAll(): List<ExerciseModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg exercise: ExerciseModel)

    @Update
    fun update(vararg exercise: ExerciseModel)

    @Delete
    fun delete(exercise: ExerciseModel)
}