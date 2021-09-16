package net.vitaldigitalmedia.secretcontacts.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.vitaldigitalmedia.secretcontacts.data.AppDatabase
import net.vitaldigitalmedia.secretcontacts.data.ContactDao
import net.vitaldigitalmedia.secretcontacts.model.Contact


class MainViewModel(private val application: Application) : ViewModel() {

    private var contactDao: ContactDao
    private lateinit var currentList: List<Contact>
    private lateinit var updatedList: LiveData<List<Contact>>
    private var appDataBase: AppDatabase? = null

    init {
        appDataBase = AppDatabase.getAppDataBase(application.applicationContext)
        contactDao = appDataBase!!.contactDao()
    }

    val currentData: List<Contact>
        get() = contactDao.getAllContacts()

    val updatedData: LiveData<List<Contact>>
        get() = contactDao.getContacts()

    fun createContact(contact: Contact) {
        Thread {
            appDataBase?.contactDao()?.insertContact(contact)
        }.start()
    }

    fun updateContact(contact: Contact) {
        Thread {
            appDataBase?.contactDao()?.updateContact(contact)
        }.start()
    }
}