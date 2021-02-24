package pl.marek.notatnik.framework

import pl.marek.core.interactors.AddNote
import pl.marek.core.interactors.GetNotes

data class Interactors(val addNote: AddNote, val getNotes: GetNotes)