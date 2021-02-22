package pl.marek.notatnik.framework

import android.app.Activity
import android.content.Context
import com.google.gson.Gson
import pl.marek.core.data.NoteDataSource
import pl.marek.core.domain.Note

class SharedPreferencesNoteDataSource(private val activity: Activity) : NoteDataSource {
    override suspend fun add(note: Note) {

        val title = note.title
        val body = note.body

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        //read
        var notesJson = sharedPref.getString("NOTES", "[]")
        var gson = Gson()
        var notesOld = gson.fromJson(notesJson, Array<Note>::class.java).asList()

        //modification
        var maxId = 0;
        for (n in notesOld) {
            if (n.id > maxId) {
                maxId = n.id
            }
        }
        val notesNew = arrayListOf<Note>()
        notesNew.addAll(notesOld)
        notesNew.add(Note(maxId + 1, title, body))

        var jsonString = gson.toJson(notesNew)

        //write
        with(sharedPref.edit()) {
            putString("NOTES", jsonString)
            apply()
        }
    }

    override suspend fun readAll(): List<Note> {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        var notesJson = sharedPref.getString("NOTES", "[]")

        var gson = Gson()
        return gson.fromJson(notesJson, Array<Note>::class.java).asList()
    }
}