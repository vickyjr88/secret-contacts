package net.vitaldigitalmedia.secretcontacts

import android.content.Context
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
        // mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
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

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

}