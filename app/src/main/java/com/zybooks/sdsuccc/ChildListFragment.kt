package com.zybooks.sdsuccc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class ChildListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.fragment_child_list, container, false)

        // Click listener for the RecyclerView
        val onClickListener = View.OnClickListener { itemView: View ->

            // Create fragment arguments containing the selected child ID
            val selectedChildId = itemView.tag as Int
            val args = Bundle()
            args.putInt(ARG_CHILD_ID, selectedChildId)

            val detailFragContainer = rootView.findViewById<View>(R.id.info_frag_container)
            if (detailFragContainer == null) {
                // Replace list with information
                Navigation.findNavController(itemView).navigate(R.id.show_child_info, args)
            } else {
                // Show details on the right
                Navigation.findNavController(detailFragContainer).navigate(R.id.fragment_child_info, args)
            }
        }
        // Send children to RecyclerView
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.child_list)
        val children = ChildRepository.getInstance(requireContext()).childList
        recyclerView.adapter = ChildAdapter(children, onClickListener)

        return rootView
    }

    private class ChildAdapter(private val childList: List<Child>,
                              private val onClickListener: View.OnClickListener) :
        RecyclerView.Adapter<ChildHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return ChildHolder(layoutInflater, parent)
        }

        override fun onBindViewHolder(holder: ChildHolder, position: Int) {
            val child = childList[position]
            holder.bind(child)
            holder.itemView.tag = child.id
            holder.itemView.setOnClickListener(onClickListener)
        }

        override fun getItemCount(): Int {
            return childList.size
        }
    }

    private class ChildHolder(inflater: LayoutInflater, parent: ViewGroup?) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_child, parent, false)) {

        private val nameTextView: TextView

        init {
            nameTextView = itemView.findViewById(R.id.child_name)
        }

        fun bind(child: Child) {
            nameTextView.text = child.name
        }
    }
}