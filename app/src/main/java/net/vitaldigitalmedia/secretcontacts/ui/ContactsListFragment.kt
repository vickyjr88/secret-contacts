package net.vitaldigitalmedia.secretcontacts.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import net.vitaldigitalmedia.secretcontacts.adapter.ContactAdapter
import net.vitaldigitalmedia.secretcontacts.databinding.FragmentContactsListBinding
import net.vitaldigitalmedia.secretcontacts.model.Contact
import net.vitaldigitalmedia.secretcontacts.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ContactsListFragment : Fragment() {

    private lateinit var contactAdapter: ContactAdapter
    private val viewModel: MainViewModel by viewModel { parametersOf(requireActivity().application) }

    private var _binding: FragmentContactsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContactsListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contactAdapter =
            ContactAdapter(::onContactItemClickListener, ::onContactItemLongClickListener)

        /*viewModel.updatedData.observe(requireActivity(), {
            displayContacts(it)
        })*/
        Thread {
            displayContacts(viewModel.currentData)
        }.start()

        setupViews()
    }

    private fun displayContacts(contacts: List<Contact>?) {
        binding.apply {
            if (contacts!!.isEmpty()) {
                noContactsMsg.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                noContactsMsg.visibility = View.GONE
                contactAdapter.addContacts(contacts)
            }
        }
    }

    private fun onContactItemLongClickListener(contact: Contact): Boolean {
        findNavController().navigate(
            ContactsListFragmentDirections.actionContactsListFragmentToAddContactFragment(
                contact
            )
        )
        return true
    }

    private fun onContactItemClickListener(contact: Contact) {
        val dialIntent = Intent(Intent.ACTION_DIAL)
        dialIntent.data = Uri.parse("tel:" + contact.phoneNumber.trim())
        startActivity(dialIntent)
    }

    private fun setupViews() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = contactAdapter

            addContact.setOnClickListener {
                findNavController().navigate(
                    ContactsListFragmentDirections.actionContactsListFragmentToAddContactFragment(
                        Contact()
                    )
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}