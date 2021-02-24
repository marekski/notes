package pl.marek.notatnik

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.new_note_fragment.*
import kotlinx.android.synthetic.main.notes_fragment.*
import pl.marek.core.domain.Note

class NewNoteFragment : Fragment(R.layout.new_note_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val PREFS = "shared_notes"
        val sharedPref = context?.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        save_btn.setOnClickListener { view ->
            val title = title_note.text.toString()
            val body = body_note.text.toString()

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
            notesNew.add(Note(maxId + 1, title, body))

            var jsonString = gson.toJson(notesNew)

            //zapis
            with(sharedPref?.edit()) {
                this?.putString("NOTES", jsonString)
                this?.apply()
            }

            //todo skorzystać z interactor

            activity?.supportFragmentManager?.popBackStack()
            val view = activity?.currentFocus
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }
}
