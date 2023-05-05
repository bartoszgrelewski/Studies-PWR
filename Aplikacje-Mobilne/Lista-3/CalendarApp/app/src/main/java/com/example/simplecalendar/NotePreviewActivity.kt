package com.example.simplecalendar

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplecalendar.MainActivity.Companion.CALENDAR_EXTRA
import com.example.simplecalendar.MainActivity.Companion.deleteObjectFromDatabase
import com.example.simplecalendar.databinding.ActivityNotePreviewBinding
import java.util.*

class NotePreviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotePreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotePreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listItemCount = intent.getSerializableExtra("list") as? MutableList<EventData>
        val calendar = intent.getSerializableExtra(CALENDAR_EXTRA) as? Calendar

        if (calendar != null) {
            binding.toolbar.subtitle = calendar.time.toSimpleDate()
        }

        val arrayList = listItemCount?.let { ArrayList(it) }
        binding.emptyStateTextView.isVisible = arrayList == null

        val rvListOfEvents = findViewById<View>(R.id.recyclerView) as RecyclerView
        val adapter = arrayList?.let { EventsAdapter(it) }
        rvListOfEvents.adapter = adapter
        rvListOfEvents.layoutManager = LinearLayoutManager(this)
    }
}

class EventsAdapter(private val listOfSelectedDayObjects: MutableList<EventData>) :
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val title: TextView = itemView.findViewById(R.id.list_1)
        val subtitle: TextView = itemView.findViewById(R.id.list_2)
        val date: TextView = itemView.findViewById(R.id.dateView)
        private val deleteButton: TextView = itemView.findViewById(R.id.removeButton)

        init {
            // deleteButton initialize it
            deleteButton.setOnClickListener(this)
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onClick(v: View?) {
            println("onClick")
            if (adapterPosition != RecyclerView.NO_POSITION) {
                val clickedEvent = listOfSelectedDayObjects[adapterPosition]

                listOfSelectedDayObjects.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                notifyDataSetChanged()

                deleteObjectFromDatabase(clickedEvent)
            }
        }
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.activity_list_events, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: EventsAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val events: EventData = listOfSelectedDayObjects[position]
        // Set item views based on your views and data model
        val titleTextView = viewHolder.title
        titleTextView.text = events.name
        val subTitleTextView = viewHolder.subtitle
        subTitleTextView.text = events.desc
        val dateTextView = viewHolder.date
        dateTextView.text = events.date
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return listOfSelectedDayObjects.size
    }
}

