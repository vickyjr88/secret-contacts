package net.vitaldigitalmedia.secretcontacts.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.vitaldigitalmedia.secretcontacts.model.Contact
import secretcontacts.R

/**
 * A view holder that represent the contact. 3 fields are:
 * - contactNumberView = the contact's phoneNumber in the list
 * - contactNameView = the contact's name
 * - phoneNumberView = the contact's phone phoneNumber
 */
class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var contactNumberView: TextView
    var contactNameView: TextView
    var phoneNumberView: TextView

    init {
        contactNumberView = itemView.findViewById(R.id.contact_number) as TextView
        contactNameView = itemView.findViewById(R.id.contact_name) as TextView
        phoneNumberView = itemView.findViewById(R.id.contact_phone_number) as TextView
    }

    fun bind(position: Int, contact: Contact) {
        contactNumberView.text = position.toString()
        contactNameView.text = contact.name
        phoneNumberView.text = contact.phoneNumber
    }
}