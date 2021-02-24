package pl.marek.notatnik.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel

open class MainViewModel(application: Application, protected val interactors: Interactors) :
    AndroidViewModel(application) {

    protected val application: NotesApplication = getApplication()

}