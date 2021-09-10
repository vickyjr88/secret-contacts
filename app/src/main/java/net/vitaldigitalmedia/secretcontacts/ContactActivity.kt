package net.vitaldigitalmedia.secretcontacts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import net.vitaldigitalmedia.secretcontacts.model.Contact
import secretcontacts.R

class ContactActivity : AppCompatActivity() {
    private var contact: Contact? = null
    private var rootView: CoordinatorLayout? = null
    private var nameEditText: EditText? = null
    private var numberEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_contact)
        contact = intent.getParcelableExtra("Contact")
        setupViews()
        if (contact != null) populateView(contact)
    }

    private fun populateView(contact: Contact?) {
        nameEditText!!.setText(contact!!.name)
        numberEditText!!.setText(contact.phoneNumber)
    }

    private fun setupViews() {
        rootView = findViewById<View>(R.id.coordinator_layout) as CoordinatorLayout

        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        nameEditText = findViewById<View>(R.id.name_edit_text) as EditText
        numberEditText = findViewById<View>(R.id.number_edit_text) as EditText

        val saveButton = findViewById<View>(R.id.save_button) as Button
        if (contact != null) saveButton.text = getString(R.string.update)
        saveButton.setOnClickListener { saveContact() }
    }

    private fun saveContact() {
        var contactName = ""
        var contactNumber = ""

        if (nameEditText!!.text != null && numberEditText!!.text != null) {
            contactName = nameEditText!!.text.toString()
            contactNumber = numberEditText!!.text.toString()
        }

        if (!contactName.isEmpty() && !contactNumber.isEmpty() && Contact.isValidNumber(
                contactNumber
            )
        ) {

            if (contact?.id == null) {
                contact = Contact(null, contactName, contactNumber)
            } else {
                contact?.name = contactName
                contact?.phoneNumber = contactNumber
            }

            val data = Intent()
            data.putExtra("Contact", contact)
            setResult(101, data)
            finish()
        } else {
            Snackbar.make(
                rootView!!,
                "Unable to save contact. Please check all fields",
                Snackbar.LENGTH_LONG
            )
                .show()
        }
    }

    companion object {
        fun createIntent(context: Context, contact: Contact?): Intent {
            val intent = Intent(context, ContactActivity::class.java)
            intent.putExtra("Contact", contact)
            return intent
        }
    }
}