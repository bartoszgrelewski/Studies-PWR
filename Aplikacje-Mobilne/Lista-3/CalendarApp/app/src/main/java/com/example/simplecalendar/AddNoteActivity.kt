package com.example.simplecalendar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.simplecalendar.MainActivity.Companion.CALENDAR_EXTRA
import com.example.simplecalendar.MainActivity.Companion.DATE
import com.example.simplecalendar.MainActivity.Companion.DESCRIPTION
import com.example.simplecalendar.MainActivity.Companion.TITLE
import com.example.simplecalendar.databinding.ActivityAddNoteBinding
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val calendar = intent.getSerializableExtra(CALENDAR_EXTRA) as Calendar
        binding.toolbar.subtitle = calendar.time.toSimpleDate()

        binding.saveButton.setOnClickListener {
            val title = binding.nameOfEvent.text.toString()
            val description = binding.descriptionOfEvent.text.toString()
            val date = binding.editTextDate.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty() && date.isNotEmpty()) {
                val returnIntent = Intent()
                returnIntent.putExtra(CALENDAR_EXTRA, calendar)

                returnIntent.putExtra(TITLE, title)
                returnIntent.putExtra(DESCRIPTION, description)
                returnIntent.putExtra(DATE, date)
                setResult(RESULT_OK, returnIntent)
            }
            finish()
        }
    }
}