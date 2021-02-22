package pl.marek.core.data

import pl.marek.core.domain.Note

class NoteRepository(private val noteDataSource: NoteDataSource) {

    suspend fun addNote(note: Note) = noteDataSource.add(note)

    suspend fun getNotes() = noteDataSource.readAll()
}