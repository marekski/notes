package pl.marek.notatnik

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.new_note_fragment.*
import pl.marek.core.domain.Note
import pl.marek.notatnik.framework.NotesViewModelFactory
import pl.marek.notatnik.presentation.NotesViewModel

class NewNoteFragment : Fragment(R.layout.new_note_fragment) {

    private lateinit var viewModel: NotesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, NotesViewModelFactory)
            .get(NotesViewModel::class.java)

        save_btn.setOnClickListener { view ->
            val title = title_note.text.toString()
            val body = body_note.text.toString()

            viewModel.addNote(Note(title, body))

            activity?.supportFragmentManager?.popBackStack()
            val view = activity?.currentFocus
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)
        }
    }
}
