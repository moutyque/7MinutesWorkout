package small.app.a7minutesworkout.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExerciseModel")
data class ExerciseModel(
    @PrimaryKey var id: Int,
    var name: String,
    var image: Int,
    var isCompleted: Boolean = false,
    var isSelected: Boolean = false
) {

}