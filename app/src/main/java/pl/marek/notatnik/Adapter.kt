package pl.marek.notatnik

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*
import pl.marek.core.domain.Note

class Adapter(private val noteList: List<Note>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val noteView = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false)
        return ViewHolder(noteView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = noteList.get(position)

        holder.titleTextView.text = currentItem.title
        holder.bodyTextView.text = currentItem.body
    }


    class ViewHolder(noteView: View) : RecyclerView.ViewHolder(noteView) {
        val titleTextView: TextView = noteView.note_title_item
        val bodyTextView: TextView = noteView.note_body_item
    }

    override fun getItemCount() = noteList.size
}