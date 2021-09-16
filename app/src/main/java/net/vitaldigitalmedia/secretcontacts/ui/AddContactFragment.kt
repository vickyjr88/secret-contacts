package net.vitaldigitalmedia.secretcontacts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import net.vitaldigitalmedia.secretcontacts.R
import net.vitaldigitalmedia.secretcontacts.databinding.FragmentAddContactBinding
import net.vitaldigitalmedia.secretcontacts.model.Contact
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddContactFragment : Fragment() {

    private lateinit var contact: Contact
    private val viewModel: ContactViewModel by viewModel { parametersOf(requireActivity().application) }
    private val args: AddContactFragmentArgs by navArgs()
    private var _binding: FragmentAddContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        contact = args.contact
        setupViews()
        if (contact.id != null) populateView(contact)
    }

    private fun populateView(contact: Contact?) {
        binding.apply {
            nameEditText.setText(contact!!.name)
            numberEditText.setText(contact.phoneNumber)
        }
    }

    private fun setupViews() {
        binding.apply {
            if (contact.id != null) saveButton.text = getString(R.string.update)
            saveButton.setOnClickListener { saveContact() }
        }
    }

    private fun saveContact() {
        var contactName = ""
        var contactNumber = ""

        binding.apply {
            if (nameEditText.text != null && numberEditText.text != null) {
                contactName = nameEditText.text.toString()
                contactNumber = numberEditText.text.toString()
            }

            if (!contactName.isEmpty() && !contactNumber.isEmpty() && Contact.isValidNumber(
                    contactNumber
                )
            ) {

                if (contact.id == null) {
                    contact = Contact(null, contactName, contactNumber)
                    viewModel.createContact(contact)
                } else {
                    contact.name = contactName
                    contact.phoneNumber = contactNumber
                    viewModel.updateContact(contact)
                }
                findNavController().navigate(AddContactFragmentDirections.actionAddContactFragmentToContactsListFragment())
            } else {
                Snackbar.make(
                    coordinatorLayout,
                    "Unable to save contact. Please check all fields",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}