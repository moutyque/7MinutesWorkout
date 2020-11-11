package small.app.a7minutesworkout.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import small.app.a7minutesworkout.R
import small.app.a7minutesworkout.databinding.FragmentBmiBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private var imperialSelected = false

/**
 * A simple [Fragment] subclass.
 * Use the [bmiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class bmiFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentBmiBinding.inflate(inflater)
        // Inflate the layout for this fragment

        binding.btnBMICalculation.setOnClickListener {
            if (!binding.etMetricUnitWeight.text.isNullOrEmpty() && !binding.etMetricUnitHeight.text.isNullOrEmpty())
                calculateBMI(
                    binding.etMetricUnitWeight.text.toString().toDouble(),
                    binding.etMetricUnitHeight.text.toString().toInt(),
                    imperialSelected
                )
        }

        binding.rbSI.setOnClickListener {
            imperialSelected = false
            unselectRadioButton(binding.rbImp)
            selectRadioButton(binding.rbSI)
            binding.tilMetricUnitHeight.hint = getString(R.string.height_in_cm)
            binding.tilMetricUnitWeight.hint = getString(R.string.weight_in_kg)
            reset()
        }

        binding.rbImp.setOnClickListener {
            imperialSelected = true
            unselectRadioButton(binding.rbSI)
            selectRadioButton(binding.rbImp)
            binding.tilMetricUnitHeight.hint = getString(R.string.height_in_inch)
            binding.tilMetricUnitWeight.hint = getString(R.string.weight_in_lb)
            reset()
        }

        return binding.root
    }

    private fun reset() {
        binding.tvBMICategory.text = ""
        binding.tvBMICategory.visibility = View.INVISIBLE

        binding.tvMessage.text = ""
        binding.tvMessage.visibility = View.INVISIBLE

        binding.tvBMIResult.text = ""
        binding.tvBMIResult.visibility = View.INVISIBLE
    }

    private fun unselectRadioButton(btn: RadioButton) {
        btn.setBackgroundResource(R.drawable.radio_btn_not_selected)
        btn.setTextColor(Color.GRAY)
    }

    private fun selectRadioButton(btn: RadioButton) {
        btn.setBackgroundResource(R.drawable.radio_btn_selected)
        btn.setTextColor(Color.WHITE)
    }

    private fun calculateBMI(weight: Double, height: Int, imperialUnit: Boolean) {
        var factor = 100.0 * 100.0 //Reduce height from cm in m
        if (imperialUnit) {
            factor = 703.0
        }

        val BMI = factor * weight / (height * height)

        binding.tvBMIResult.text = BMI.toString().subSequence(0, BMI.toString().indexOf(".") + 2)

        when {
            BMI < 16.0 -> {
                binding.tvBMICategory.text = "Severe Thinness"
                binding.tvMessage.text = "You should eat more"
            }
            BMI < 17.0 -> {
                binding.tvBMICategory.text = "Moderate Thinness"
                binding.tvMessage.text = "You should eat more"

            }
            BMI < 18.5 -> {
                binding.tvBMICategory.text = "Mild Thinness"
                binding.tvMessage.text = "You should eat more"


            }
            BMI < 25 -> {
                binding.tvBMICategory.text = "Normal"
                binding.tvMessage.text = "You are in great shape"


            }
            BMI < 30 -> {
                binding.tvBMICategory.text = "Overweight"
                binding.tvMessage.text = "You should be carrefull to your weight"


            }
            BMI < 35 -> {
                binding.tvBMICategory.text = "Overweight Class I"
                binding.tvMessage.text = "You should be carrefull to your weight"


            }
            BMI < 40 -> {
                binding.tvBMICategory.text = "Obese Class II"
                binding.tvMessage.text = "You should be carrefull to your weight"


            }
            else -> {
                binding.tvBMICategory.text = "Obese Class III"
                binding.tvMessage.text = "You should be carrefull to your weight"

            }

        }

        binding.tvBMICategory.visibility = View.VISIBLE
        binding.tvMessage.visibility = View.VISIBLE
        binding.tvBMIResult.visibility = View.VISIBLE
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment bmiFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            bmiFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

