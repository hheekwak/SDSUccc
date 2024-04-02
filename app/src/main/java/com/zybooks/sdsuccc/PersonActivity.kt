package com.zybooks.sdsuccc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.zybooks.sdsuccc.model.Person
import com.zybooks.sdsuccc.model.Cclass
import com.zybooks.sdsuccc.viewmodel.PersonListViewModel

class PersonActivity : AppCompatActivity() {

    private lateinit var personListViewModel: PersonListViewModel
    private lateinit var cclass: Cclass
    private lateinit var personList: List<Person>
    private lateinit var infoLabelTextView: TextView
    private lateinit var infoTextView: TextView
    private lateinit var infoButton: Button
    private lateinit var personTextView: TextView
    private lateinit var showPersonLayout: ViewGroup
    private lateinit var noPersonLayout: ViewGroup
    private var currentPersonIndex = 0

    companion object {
        const val EXTRA_CCLASS_ID = "com.zybooks.sdsuccc.cclass_id"
        const val EXTRA_CCLASS_TEXT = "com.zybooks.sdsuccc.cclass_text"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        personTextView = findViewById(R.id.person_text_view)
        infoLabelTextView = findViewById(R.id.info_label_text_view)
        infoTextView = findViewById(R.id.info_text_view)
        infoButton = findViewById(R.id.info_button)
        showPersonLayout = findViewById(R.id.show_person_layout)

        // Add click callbacks
        infoButton.setOnClickListener { toggleInfoVisibility() }
        findViewById<Button>(R.id.add_person_button).setOnClickListener { addPerson() }

        // CclassActivity should provide the cclass ID and text
        val cclassId = intent.getLongExtra(EXTRA_CCLASS_ID, 0)
        val cclassText = intent.getStringExtra(EXTRA_CCLASS_TEXT)
        cclass = Cclass(cclassId, cclassText!!)

        // Get all person for this class
        personListViewModel = PersonListViewModel(application)
        personList = personListViewModel.getPersons(cclassId)

        // Display person
        updateUI()
    }

    private fun updateUI() {
        showPerson(currentPersonIndex)

        if (personList.isEmpty()) {
            updateAppBarTitle()
            displayPerson(false)
        } else {
            displayPerson(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.person_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //  Determine which app bar item was chosen
        return when (item.itemId) {
            R.id.previous -> {
                showPerson(currentPersonIndex - 1)
                true
            }
            R.id.next -> {
                showPerson(currentPersonIndex + 1)
                true
            }
            R.id.add -> {
                addPerson()
                true
            }
            R.id.edit -> {
                editPerson()
                true
            }
            R.id.delete -> {
                deletePerson()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayPerson(display: Boolean) {
        if (display) {
            showPersonLayout.visibility = View.VISIBLE
            noPersonLayout.visibility = View.GONE
        } else {
            showPersonLayout.visibility = View.GONE
            noPersonLayout.visibility = View.VISIBLE
        }
    }

    private fun updateAppBarTitle() {

        // Display cclass and number of persons in app bar
        val title = resources.getString(R.string.person_number, cclass.text,
            currentPersonIndex + 1, personList.size)
        setTitle(title)
    }

    private fun addPerson() {
        // TODO: Add question
    }

    private fun editPerson() {
        // TODO: Edit question
    }

    private fun deletePerson() {
        // TODO: Delete question
    }

    private fun showPerson(personIndex: Int) {

        // Show person at the given index
        if (personList.isNotEmpty()) {
            var newPersonIndex = personIndex

            if (personIndex < 0) {
                newPersonIndex = personList.size - 1
            } else if (personIndex >= personList.size) {
                newPersonIndex = 0
            }

            currentPersonIndex = newPersonIndex
            updateAppBarTitle()

            val person = personList[currentPersonIndex]
            personTextView.text = person.name
            infoTextView.text = person.dob + person.allergy

        } else {
            // No person yet
            currentPersonIndex = -1
        }
    }

    private fun toggleInfoVisibility() {
        if (infoTextView.visibility == View.VISIBLE) {
            infoButton.setText(R.string.show_information)
            infoTextView.visibility = View.INVISIBLE
            infoLabelTextView.visibility = View.INVISIBLE
        } else {
            infoButton.setText(R.string.hide_information)
            infoTextView.visibility = View.VISIBLE
            infoLabelTextView.visibility = View.VISIBLE
        }
    }
}