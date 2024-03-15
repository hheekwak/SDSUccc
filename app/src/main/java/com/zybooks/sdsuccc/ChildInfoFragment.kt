package com.zybooks.sdsuccc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ChildInfoFragment : Fragment() {

    private var child: Child? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var childId = 1

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
}