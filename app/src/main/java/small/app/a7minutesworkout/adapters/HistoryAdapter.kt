package small.app.a7minutesworkout.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history_row.view.*
import small.app.a7minutesworkout.R
import small.app.a7minutesworkout.models.DateModel

class HistoryAdapter(val context: Context, val items: List<DateModel>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val llHistoryMainItem = view.ll_history_item_main
        val tvItem = view.tvItem
        val tvPosition = view.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_history_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = items.get(position)
        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date.date

        if (position % 2 == 0) {
            holder.llHistoryMainItem.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        } else {
            holder.llHistoryMainItem.setBackgroundColor(
                Color.WHITE
            )
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }


}