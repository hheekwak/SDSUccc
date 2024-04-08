package com.zybooks.sdsuccc

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

const val ARG_CHILD_ID = "child_id"

class ChildInfoFragment : Fragment() {

    private var child: Child? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var childId = 1


        // Get the child ID from the fragment arguments
        arguments?.let { childId = it.getInt(ARG_CHILD_ID) }

        // Get the selected child
        child = ChildRepository.getInstance(requireContext()).getChild(childId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_child_info, container, false)

        if (child != null) {
            val nameTextView = rootView.findViewById<TextView>(R.id.child_name)
            nameTextView.text = child!!.name
            val informationTextView = rootView.findViewById<TextView>(R.id.child_information)
            informationTextView.text = child!!.information
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Registering for context menu here after the view is created
        registerForContextMenu(view)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Use the MenuInflater from the fragment's context
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.message -> {
                // Handle the message action
                true
            }
            R.id.details -> {
                // Handle the details action
                true
            }
            R.id.deactivate -> {
                // Handle the deactivate action
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}