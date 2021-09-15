package net.vitaldigitalmedia.secretcontacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.vitaldigitalmedia.secretcontacts.R
import net.vitaldigitalmedia.secretcontacts.model.Contact
import java.util.*

class ContactAdapter(
    val onItemClickListener: (contact: Contact) -> Unit,
    val onItemLongClickListener: (contact: Contact) -> Boolean
) : RecyclerView.Adapter<ContactViewHolder>() {
    val contactList: MutableList<Contact> = ArrayList()

    /** TODO:
     * - add onClick functionality that brings user to dialer
     * - add onLongClick functionality that brings user to edit screen
     */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_contact, parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contactList.get(position)
        holder.itemView.setOnClickListener { onItemClickListener(contact) }
        holder.itemView.setOnLongClickListener { onItemLongClickListener(contact) }
        holder.bind(position + 1, contact)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    fun add(contact: Contact) {
        contactList.add(contact)
        notifyDataSetChanged()
    }

    fun remove(contact: Contact) {
        contactList.remove(contact)
    }

    fun addContacts(contacts: List<Contact>) {
        this.contactList.apply {
            clear()
            addAll(contacts)
        }
        notifyDataSetChanged()
    }
}