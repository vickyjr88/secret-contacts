package net.vitaldigitalmedia.secretcontacts.data

import androidx.lifecycle.LiveData
import androidx.room.*
import net.vitaldigitalmedia.secretcontacts.model.Contact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Delete
    fun deleteContact(contact: Contact)

    @Query("SELECT * FROM Contact WHERE name == :name")
    fun getContactByName(name: String): LiveData<List<Contact>>

    @Query("SELECT * FROM Contact")
    fun getContacts(): LiveData<List<Contact>>


    @Query("SELECT * FROM Contact")
    fun getAllContacts(): List<Contact>

}
