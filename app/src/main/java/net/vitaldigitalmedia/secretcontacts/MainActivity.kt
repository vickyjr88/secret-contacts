package net.vitaldigitalmedia.secretcontacts

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import net.vitaldigitalmedia.secretcontacts.adapter.ContactAdapter
import net.vitaldigitalmedia.secretcontacts.data.AppDatabase
import net.vitaldigitalmedia.secretcontacts.model.Contact
import net.vitaldigitalmedia.secretcontacts.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var noContactsMsg: RelativeLayout
    private lateinit var recyclerView: RecyclerView
    private var contactAdapter: ContactAdapter? = null
    private var db: AppDatabase? = null
    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        contactAdapter =
            ContactAdapter(::onContactItemClickListener, ::onContactItemLongClickListener)

        mainViewModel.updatedData.observe(this, {
            displayContacts(it)
        })

        setupViews()
    }

    private fun displayContacts(contacts: List<Contact>?) {
        noContactsMsg = findViewById(R.id.no_contacts_msg)
        if (contacts!!.isEmpty()) {
            noContactsMsg.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            noContactsMsg.visibility = View.GONE
            contactAdapter!!.addContacts(contacts)
        }
    }

    private fun onContactItemLongClickListener(contact: Contact): Boolean {
        startActivityForResult(ContactActivity.createIntent(this, contact), 101)
        return true
    }

    private fun onContactItemClickListener(contact: Contact) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + contact.phoneNumber.trim())
        startActivity(dialIntent)
    }

    private fun setupViews() {
//        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
//        setSupportActionBar(toolbar)

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = contactAdapter

        val addContactButton = findViewById<View>(R.id.add_contact) as FloatingActionButton
        addContactButton.setOnClickListener {
            startActivityForResult(ContactActivity.createIntent(this, null), 101)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data?.getParcelableExtra<Contact>("Contact") != null) {
            val contact = data.getParcelableExtra<Contact>("Contact")
            if (contact != null) {
                if (contact.id != null) {
                    mainViewModel.updateContact(contact)
                } else {
                    mainViewModel.createContact(contact)
                }
            }
        }
    }

}