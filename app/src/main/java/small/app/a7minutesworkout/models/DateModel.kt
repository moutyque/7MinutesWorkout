package small.app.a7minutesworkout.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DateModel")
data class DateModel(
    var date: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}