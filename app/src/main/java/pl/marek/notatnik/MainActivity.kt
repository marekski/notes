package pl.marek.notatnik

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<NotesFragment>(R.id.fragment_container_view)
            }
        }
    }


//    private fun loadFragmentWithAddToBackStack(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container_view, fragment)
//        transaction.setReorderingAllowed(true)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }
}