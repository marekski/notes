package pl.marek.notatnik.framework

import android.app.Application
import pl.marek.core.data.NoteRepository
import pl.marek.core.interactors.AddNote
import pl.marek.core.interactors.GetNotes

class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val noteRepository = NoteRepository(SharedPreferencesNoteDataSource(this))

        NotesViewModelFactory.inject(
            this,
            Interactors(AddNote(noteRepository), GetNotes(noteRepository))
        )
    }
}