package com.zybooks.sdsuccc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zybooks.sdsuccc.model.Cclass
import com.zybooks.sdsuccc.viewmodel.CclassListViewModel

class CclassActivity : AppCompatActivity(),
    CclassDialogFragment.OnCclassEnteredListener {

    private var cclassAdapter = CclassAdapter(mutableListOf())
    private lateinit var cclassRecyclerView: RecyclerView
    private lateinit var cclassColors: IntArray
    private lateinit var cclassListViewModel: CclassListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cclass)

        cclassListViewModel = CclassListViewModel(application)

        cclassColors = resources.getIntArray(R.array.cclassColors)

        findViewById<FloatingActionButton>(R.id.add_cclass_button).setOnClickListener {
            addCclassClick() }

        cclassRecyclerView = findViewById(R.id.cclass_recycler_view)
        cclassRecyclerView.layoutManager = GridLayoutManager(applicationContext, 2)

        // Show the classes
        updateUI(cclassListViewModel.getCclasses())
    }

    private fun updateUI(cclassList: List<Cclass>) {
        cclassAdapter = CclassAdapter(cclassList as MutableList<Cclass>)
        cclassRecyclerView.adapter = cclassAdapter
    }

    override fun onCclassEntered(cclassText: String) {
        if (cclassText.isNotEmpty()) {
            val cclass = Cclass(0, cclassText)
            cclassListViewModel.addCclass(cclass)
            updateUI(cclassListViewModel.getCclasses())

            Toast.makeText(this, "Added $cclassText", Toast.LENGTH_SHORT).show()
        }
    }

    private fun addCclassClick() {
        val dialog = CclassDialogFragment()
        dialog.show(supportFragmentManager, "cclassDialog")
    }

    private inner class CclassHolder(inflater: LayoutInflater, parent: ViewGroup?) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.recycler_view_items, parent, false)),
        View.OnClickListener {

        private var cclass: Cclass? = null
        private val cclassTextView: TextView

        init {
            itemView.setOnClickListener(this)
            cclassTextView = itemView.findViewById(R.id.cclass_text_view)
        }

        fun bind(cclass: Cclass, position: Int) {
            this.cclass = cclass
            cclassTextView.text = cclass.text

            // Make the background color dependent on the length of the cclass string
            val colorIndex = cclass.text.length % cclassColors.size
            cclassTextView.setBackgroundColor(cclassColors[colorIndex])
        }

        override fun onClick(view: View) {
            // Start PersonActivity with the selected cclass
            val intent = Intent(this@CclassActivity, PersonActivity::class.java)
            intent.putExtra(PersonActivity.EXTRA_CCLASS_ID, cclass!!.id)
            intent.putExtra(PersonActivity.EXTRA_CCLASS_TEXT, cclass!!.text)

            startActivity(intent)
        }
    }

    private inner class CclassAdapter(private val cclassList: MutableList<Cclass>) :
        RecyclerView.Adapter<CclassHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CclassHolder {
            val layoutInflater = LayoutInflater.from(applicationContext)
            return CclassHolder(layoutInflater, parent)
        }

        override fun onBindViewHolder(holder: CclassHolder, position: Int) {
            holder.bind(cclassList[position], position)
        }

        override fun getItemCount(): Int {
            return cclassList.size
        }
    }
}