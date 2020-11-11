package small.app.a7minutesworkout.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import small.app.a7minutesworkout.Constants
import small.app.a7minutesworkout.SqliteOpenHelper
import small.app.a7minutesworkout.activities.MainActivity
import small.app.a7minutesworkout.database.getInstance
import small.app.a7minutesworkout.databinding.FragmentEndBinding
import small.app.a7minutesworkout.models.DateModel
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
lateinit var binding: FragmentEndBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EndFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EndFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        binding = FragmentEndBinding.inflate(inflater)
        // Inflate the layout for this fragment
        binding.btnFinish.setOnClickListener {
            onEnd(it)
        }
        addDateToDB()
        Constants.reset()



        return binding.root
    }

    fun onEnd(view: View) {
        try {
            val k = Intent(context, MainActivity::class.java)
            startActivity(k)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun addDateToDB() {
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)

        GlobalScope.launch {
            getInstance(requireContext()).dateDao().insert(DateModel(date))
        }
        val dbHandler = SqliteOpenHelper(requireContext(), null)
        dbHandler.addDate(
            date.toString()
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EndFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EndFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}