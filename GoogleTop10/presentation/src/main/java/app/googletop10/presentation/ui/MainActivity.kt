package app.googletop10.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.googletop10.presentation.R
import app.googletop10.presentation.ui.search.SearchFragment
import app.googletop10.presentation.util.replace

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            replace(SearchFragment.newInstance())
        }
    }
}