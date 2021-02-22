package pl.marek.core.data

import pl.marek.core.domain.Note

interface NoteDataSource {

    suspend fun add(note: Note)

    suspend fun readAll(): List<Note>
}