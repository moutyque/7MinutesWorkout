package small.app.a7minutesworkout.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import small.app.a7minutesworkout.models.ExerciseModel

class ExerciseViewModel(_exercice: ExerciseModel) : ViewModel() {
    var progress = MutableLiveData<Int>()
    var countDown = MutableLiveData<Int>()
    var exercice = _exercice
}