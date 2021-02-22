package pl.marek.notatnik

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.notes_fragment.*
import pl.marek.core.domain.Note

class NotesFragment : Fragment(R.layout.notes_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
//        var notesJson = sharedPref.getString("NOTES", "android007")
//
//        var gson = Gson()
//        var notes = gson.fromJson(notesJson, Array<Note>::class.java).asList()

        // todo skorzystać z interactor

        recycler_view.adapter = Adapter(notes)
        recycler_view.layoutManager = LinearLayoutManager(context)


        fab.setOnClickListener { view ->
            startNewNoteFragment()
        }
    }


    private fun startNewNoteFragment() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_container_view, NewNoteFragment())
        transaction?.setReorderingAllowed(true)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
