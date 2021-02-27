package pl.marek.notatnik

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.notes_fragment.*
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
    }

    private fun startNewNoteFragment() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_container_view, NewNoteFragment())
        transaction?.setReorderingAllowed(true)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
