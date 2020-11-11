package small.app.a7minutesworkout.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import small.app.a7minutesworkout.Constants.Companion.exercisesList
import small.app.a7minutesworkout.Constants.Companion.getDrawable
import small.app.a7minutesworkout.activities.ExerciseActivity
import small.app.a7minutesworkout.databinding.FragmentExerciseBinding
import small.app.a7minutesworkout.models.ExerciseModel
import small.app.a7minutesworkout.viewmodel.ExerciseViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private lateinit var restTimer: CountDownTimer


/**
 * A simple [Fragment] subclass.
 * Use the [ExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExerciseFragment : Fragment() {
    val timerTotal: Long = 3_000
    val timerStep: Long = 1000
    val args: ExerciseFragmentArgs by navArgs()
    lateinit var exerciseModel: ExerciseModel

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentExerciseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        exerciseModel = exercisesList[args.exercicesIndex]

        (activity as ExerciseActivity?)!!.speakOut(exerciseModel.name)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentExerciseBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.model = ExerciseViewModel(exerciseModel)
        // Inflate the layout for this fragment
        binding.model!!.progress.value = 100
//TODO : add the link between constante exercices and the modelView
        binding.exercicePicture.setImageDrawable(getDrawable(requireContext(), exerciseModel.image))
        binding.textView.text = exerciseModel.name
        (activity as ExerciseActivity?)!!.player.start()
        startCountDown()
        return binding.root
    }

    private fun startCountDown() {

        //Object means that we are creating an object of type CountDownTimer as a subclass
        restTimer = object : CountDownTimer(timerTotal, timerStep) {
            var count = timerTotal / timerStep
            var progress = 0
            val proressStep = (100 / count).toInt()
            override fun onTick(p0: Long) {
                binding.model!!.countDown.value = count.toInt()
                binding.model!!.progress.value = progress
                count--
                progress += proressStep

            }

            override fun onFinish() {

                binding.model!!.countDown.value = 0
                binding.model!!.progress.value = 100
                val actionExerciseFragmentToRestFragment =
                    ExerciseFragmentDirections.actionExerciseFragmentToRestFragment()
                actionExerciseFragmentToRestFragment.exercicesIndex = args.exercicesIndex
                
                exercisesList[actionExerciseFragmentToRestFragment.exercicesIndex].isSelected =
                    false
                exercisesList[actionExerciseFragmentToRestFragment.exercicesIndex].isCompleted =
                    true
                (activity as ExerciseActivity?)!!.exerciseAdapter.notifyDataSetChanged()

                if (actionExerciseFragmentToRestFragment.exercicesIndex == exercisesList.size - 1) {
                    view!!.findNavController()
                        .navigate(ExerciseFragmentDirections.actionExerciseFragmentToEndFragment())
                } else {


                    view!!.findNavController()
                        .navigate(actionExerciseFragmentToRestFragment)
                }

            }
        }.start()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        restTimer.cancel()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExerciseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExerciseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}