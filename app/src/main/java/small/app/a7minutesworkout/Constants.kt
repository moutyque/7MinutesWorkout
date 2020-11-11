package small.app.a7minutesworkout

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import small.app.a7minutesworkout.models.ExerciseModel

class Constants {
    companion object {
        val exercisesList = defaultExerciseList()

        fun defaultExerciseList(): ArrayList<ExerciseModel> {
            val exerciseList = ArrayList<ExerciseModel>()
            val jumpingJacks = ExerciseModel(1, "JumpingJacks", R.drawable.ic_jumping_jacks)
            val wallSit = ExerciseModel(2, "Wall sit", R.drawable.ic_wall_sit)
            val pushUp = ExerciseModel(3, "Push Up", R.drawable.ic_push_up)
            val crunch = ExerciseModel(4, "Crunch", R.drawable.ic_abdominal_crunch)
            val stepUpOnChair =
                ExerciseModel(5, "Step up on chair", R.drawable.ic_step_up_onto_chair)
            val squat = ExerciseModel(6, "Squat", R.drawable.ic_triceps_dip_on_chair)
            val dips = ExerciseModel(7, "Dip on chair", R.drawable.ic_triceps_dip_on_chair)
            val plank = ExerciseModel(8, "Plank", R.drawable.ic_plank)
            val highKnee =
                ExerciseModel(9, "High knees", R.drawable.ic_high_knees_running_in_place)
            val lunges = ExerciseModel(10, "Lunges", R.drawable.ic_lunge)
            val pushUpAndRotation =
                ExerciseModel(11, "Push up and rotation", R.drawable.ic_push_up_and_rotation)
            val sidePlank = ExerciseModel(12, "Side plank", R.drawable.ic_side_plank)

            exerciseList.add(jumpingJacks)
            exerciseList.add(wallSit)
            exerciseList.add(pushUp)
            exerciseList.add(crunch)
            exerciseList.add(stepUpOnChair)
            exerciseList.add(squat)
            exerciseList.add(dips)
            exerciseList.add(plank)
            exerciseList.add(highKnee)
            exerciseList.add(lunges)
            exerciseList.add(pushUpAndRotation)
            exerciseList.add(sidePlank)

            return exerciseList
        }

        fun getRestExercise(): ExerciseModel {
            return ExerciseModel(0, "Rest", 0)
        }

        fun getDrawable(context: Context, id: Int): Drawable? {
            return ResourcesCompat.getDrawable(
                context.getResources(),
                id,
                null
            )
        }

        fun reset() {
            for (e in exercisesList) {
                e.isSelected = false
                e.isCompleted = false
            }
        }
    }

}