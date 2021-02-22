package pl.marek.core.interactors

import pl.marek.core.data.NoteRepository
import pl.marek.core.domain.Note

class AddNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}