package com.example.simplecalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.builders.DatePickerBuilder
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener
import com.example.simplecalendar.databinding.ActivityMainBinding
import java.io.Serializable
import java.util.*

class MainActivity : AppCompatActivity(), OnDayClickListener, OnSelectDateListener {

    private lateinit var binding: ActivityMainBinding

    /* database which consists of
    EventDay - object with date and other fields related to calendar
    EventData - object with fields related to events
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // set up the FAB button to open the date picker
        binding.fabButton.setOnClickListener { openDatePicker() }

        // set up the calendar view to handle day clicks
        binding.calendarView.setOnDayClickListener(this)

        // update the calendar events to display any events already in the database
        updateCalendarEvents()
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putSerializable("database", database as Serializable)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        database = savedInstanceState.getSerializable("database") as MutableMap<EventDay, MutableList<EventData>>
//    }


    // function to open the date picker dialog
    private fun openDatePicker() {
        DatePickerBuilder(this, this)
            .pickerType(CalendarView.ONE_DAY_PICKER)
            .headerColor(R.color.primary)
            .todayLabelColor(R.color.secondary)
            .selectionColor(R.color.secondary_light)
            .dialogButtonsColor(R.color.secondary)
            .build()
            .show()
    }

    // handle when a day on the calendar is clicked
    override fun onDayClick(eventDay: EventDay) {
        // get the list of events for the selected day
        val eventList = database[eventDay]

        // create an intent to open the NotePreviewActivity
        val intent = Intent(this, NotePreviewActivity::class.java)
        // pass the selected date to the NotePreviewActivity
        intent.putExtra(CALENDAR_EXTRA, eventDay.calendar)
        // set the current event day in the database
        setEventDay(eventDay)
        // pass the list of events for the selected day to the NotePreviewActivity
        intent.putExtra("list", eventList as? Serializable)
        // start the NotePreviewActivity
        startActivity(intent)

        // update the calendar events to display any changes made in the NotePreviewActivity
        updateCalendarEvents()
    }

    // handle when a date range is selected in the date picker dialog
    override fun onSelect(calendar: List<Calendar>) {
        // create an intent to open the AddNoteActivity
        val intent = Intent(this, AddNoteActivity::class.java)
        // pass the selected date to the AddNoteActivity
        intent.putExtra(CALENDAR_EXTRA, calendar.first())
        // start the AddNoteActivity with a request code to receive the result
        startActivityForResult(intent, RESULT_CODE)

        // update the calendar events to display any changes made in the AddNoteActivity
        updateCalendarEvents()
    }

    @Deprecated("Deprecated in Java") // Deprecation annotation to indicate that this method is no longer recommended for use
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // This method is called when an activity that was launched with startActivityForResult() method exits and returns a result
        if (resultCode == RESULT_OK && requestCode == RESULT_CODE) {
            // Check if the result was successful and the request code matches
            val eventDataList = mutableListOf<EventData>() // Create a new list to store event data
            val title = data?.getStringExtra(TITLE) ?: return // Extract the title of the event from the intent data
            val description = data.getStringExtra(DESCRIPTION) ?: return // Extract the description of the event from the intent data
            val date = data.getStringExtra(DATE) ?: return // Extract the date of the event from the intent data
            val calendar = data.getSerializableExtra(CALENDAR_EXTRA) as Calendar // Extract the calendar object from the intent data
            val eventDay = EventDay(calendar, applicationContext.getDot()) // Create an EventDay object using the calendar object and a custom dot drawable
            val eventData = EventData(title, description, date) // Create an EventData object using the extracted data

            if (database[eventDay] != null && database[eventDay]?.isEmpty() == false) {
                // Check if there is already an event stored for the selected day
                database[eventDay]?.add(eventData) // If an event already exists, add the new event to the existing list
            } else {
                eventDataList.add(eventData) // If there are no events for the selected day, add the new event to a new list
                database[eventDay] = eventDataList // Store the new list in the database for the selected day
            }
            updateCalendarEvents() // Update the calendar view to display the new events
        }
    }

    private fun updateCalendarEvents() {
        binding.calendarView.setEvents(database.keys.toList()) // Update the calendar view to display the events stored in the database
    }

    companion object {
        const val CALENDAR_EXTRA = "calendar" // Constant value for the calendar extra in the intent data
        const val TITLE = "title" // Constant value for the title extra in the intent data
        const val DESCRIPTION = "description" // Constant value for the description extra in the intent data
        const val DATE = "date" // Constant value for the date extra in the intent data
        const val RESULT_CODE = 8 // Constant value for the result code used with startActivityForResult() method

        @JvmStatic
        var database = mutableMapOf<EventDay, MutableList<EventData>>() // Map to store the events for each day
        private var clickedDayByUserGlobal: EventDay? = null // Variable to store the selected day for user interaction

        fun deleteObjectFromDatabase(clickedEvent: EventData) {
            // Remove the clicked event from the database for the selected day
            database[clickedDayByUserGlobal]?.remove(clickedEvent)
            // If there are no more events for the selected day, remove the day from the database
            if (database[clickedDayByUserGlobal]?.isEmpty() == true) {
                database.keys.remove(clickedDayByUserGlobal)
            }
        }

        fun setEventDay(eventDay: EventDay) {
            // Set the selected day for user interaction
            clickedDayByUserGlobal = eventDay
        }
    }
}