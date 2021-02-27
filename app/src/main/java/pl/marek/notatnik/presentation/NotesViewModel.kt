package pl.marek.notatnik.presentation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.marek.core.domain.Note
import pl.marek.notatnik.framework.Interactors
import pl.marek.notatnik.framework.MainViewModel

class NotesViewModel(application: Application, interactors: Interactors) :
    MainViewModel(application, interactors) {

    val notes: MutableLiveData<List<Note>> = MutableLiveData()

    fun loadNotes() {
        GlobalScope.launch {
            notes.postValue(interactors.getNotes())
        }
    }

    fun addNote(note: Note) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                interactors.addNote(note)
            }
            loadNotes()
        }
    }
}