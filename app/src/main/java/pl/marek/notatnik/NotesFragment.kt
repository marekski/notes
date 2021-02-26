package pl.marek.notatnik

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.notes_fragment.*
import pl.marek.core.domain.Note
import pl.marek.notatnik.framework.NotesViewModelFactory
import pl.marek.notatnik.presentation.NotesViewModel

class NotesFragment : Fragment(R.layout.notes_fragment) {

    private lateinit var viewModel: NotesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, NotesViewModelFactory)
            .get(NotesViewModel::class.java)

        recycler_view.layoutManager = LinearLayoutManager(context)

        viewModel.loadNotes()
        viewModel.notes.observe(this, Observer { notes ->
            recycler_view.adapter = Adapter(notes)
        })

        fab.setOnClickListener { view ->
            startNewNoteFragment()
        }

        button_insert.setOnClickListener { view ->
            insertNote()
        }
    }

    private fun startNewNoteFragment() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_container_view, NewNoteFragment())
        transaction?.setReorderingAllowed(true)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun insertNote() {
        val PREFS = "shared_notes"
        val sharedPref = context?.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        //odczyt
        var notesJson = sharedPref?.getString("NOTES", "[]")
        var gson = Gson()
        var notesOld = gson.fromJson(notesJson, Array<Note>::class.java).asList()

        //modyfikacja

        var maxId = 0;
        for (n in notesOld) {
            if (n.id > maxId) {
                maxId = n.id
            }
        }
        val notesNew = arrayListOf<Note>()
        notesNew.addAll(notesOld)
        notesNew.add(Note(maxId + 1, "insert z widoku listy", "insert z widoku listy"))

        var jsonString = gson.toJson(notesNew)

        //zapis
        with(sharedPref?.edit()) {
            this?.putString("NOTES", jsonString)
            this?.apply()
        }
    }
}
