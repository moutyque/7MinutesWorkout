package small.app.a7minutesworkout.activities

import android.app.Dialog
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import small.app.a7minutesworkout.Constants
import small.app.a7minutesworkout.R
import small.app.a7minutesworkout.adapters.ExerciceStatusAdapter
import small.app.a7minutesworkout.databinding.ActivityExerciseBinding
import small.app.a7minutesworkout.databinding.CustomBackPressedBinding
import java.util.*

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var binding: ActivityExerciseBinding
    private var tts: TextToSpeech? = null // Variable for TextToSpeech
    lateinit var player: MediaPlayer
    lateinit var exerciseAdapter: ExerciceStatusAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar2)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar2.setNavigationOnClickListener {
            onBackPressed()
        }
        tts = TextToSpeech(this, this)

        player = MediaPlayer.create(applicationContext, R.raw.press_start)
        setUpExerciseStatusRecyclerView()
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onInit(statut: Int) {
        if (statut == TextToSpeech.SUCCESS) {
            // set US English as language for tts
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "The Language specified is not supported!")
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    /**
     * Function is used to speak the text what we pass to it.
     */
    fun speakOut(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }


    private fun setUpExerciseStatusRecyclerView() {
        rvExercisesStatus.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciceStatusAdapter(Constants.exercisesList, applicationContext)
        rvExercisesStatus.adapter = exerciseAdapter
    }

    override fun onBackPressed() {

        val customBinding = CustomBackPressedBinding.inflate(layoutInflater)
        val diag = Dialog(this)
        diag.setContentView(customBinding.root)


        //Cancel the whole workout
        customBinding.tvYes.setOnClickListener {
            Constants.reset()
            this.finish()
            diag.dismiss()
        }
        //Continue
        customBinding.tvNo.setOnClickListener {
            diag.dismiss()
        }
        diag.show()

    }
}