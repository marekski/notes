package pl.marek.notatnik

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.notes_fragment.*
import pl.marek.notatnik.framework.NotesViewModelFactory
import pl.marek.notatnik.presentation.NotesViewModel

class NotesFragment : Fragment(R.layout.notes_fragment) {

    private lateinit var viewModel: NotesViewModel

    // todo https://dev.to/doodg/sharedprefrences-becomes-live-with-live-data-545g

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this, NotesViewModelFactory)
            .get(NotesViewModel::class.java)
        var notes = viewModel.notes

        recycler_view.adapter = Adapter(notes.value)
        recycler_view.layoutManager = LinearLayoutManager(context)


        fab.setOnClickListener { view ->
            startNewNoteFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun startNewNoteFragment() {
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment_container_view, NewNoteFragment())
        transaction?.setReorderingAllowed(true)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}
