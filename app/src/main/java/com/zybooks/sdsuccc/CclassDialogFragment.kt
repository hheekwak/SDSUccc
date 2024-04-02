package com.zybooks.sdsuccc

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class CclassDialogFragment: DialogFragment() {

    interface OnCclassEnteredListener {
        fun onCclassEntered(cclassText: String)
    }

    private lateinit var listener: OnCclassEnteredListener

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cclassEditText = EditText(requireActivity())
        cclassEditText.inputType = InputType.TYPE_CLASS_TEXT
        cclassEditText.maxLines = 1
        return AlertDialog.Builder(requireActivity())
            .setTitle(R.string.cclass)
            .setView(cclassEditText)
            .setPositiveButton(R.string.create) { dialog, whichButton ->
                // Notify listener
                val cclass = cclassEditText.text.toString()
                listener.onCclassEntered(cclass.trim())
            }
            .setNegativeButton(R.string.cancel, null)
            .create()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnCclassEnteredListener
    }
}