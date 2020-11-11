package small.app.a7minutesworkout.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_status.view.*
import small.app.a7minutesworkout.R
import small.app.a7minutesworkout.models.ExerciseModel

class ExerciceStatusAdapter(val items: ArrayList<ExerciseModel>, val context: Context) :
    RecyclerView.Adapter<ExerciceStatusAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem = view.tvItem

    }

    //Create the layout of the Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_exercise_status, parent, false)
        )
    }

    //Render data of the viewHolder displayed
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.tvItem.text = model.id.toString()

        if (model.isCompleted) {
            holder.tvItem.background =
                ContextCompat.getDrawable(context,
                    R.drawable.item_circular_color_accent_background)
            holder.tvItem.setTextColor(Color.WHITE)
        } else if (model.isSelected) {
            holder.tvItem.background =
                ContextCompat.getDrawable(context,
                    R.drawable.item_circular_thin_color_accent_border)
            holder.tvItem.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}