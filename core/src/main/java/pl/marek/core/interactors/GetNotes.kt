package pl.marek.core.interactors

import pl.marek.core.data.NoteRepository

class GetNotes(private val noteRepository: NoteRepository) {

    suspend operator fun invoke() = noteRepository.getNotes()
}