package com.example.taskmanagementapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner

class AddTaskDialogFragment : DialogFragment() {

    private lateinit var editTextTaskName: EditText
    private lateinit var spinnerPriority: Spinner
    private lateinit var radioGroupPriority: RadioGroup
    private lateinit var buttonAddTask: Button

    interface AddTaskDialogListener {
        fun onTaskAdded(taskName: String, priority: String, level: String)
    }

    private lateinit var listener: AddTaskDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as AddTaskDialogListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        editTextTaskName = view.findViewById(R.id.editTextTaskName)
        spinnerPriority = view.findViewById(R.id.spinnerPriority)
        radioGroupPriority = view.findViewById(R.id.radioGroupPriority)
        buttonAddTask = view.findViewById(R.id.buttonAddTask)

        buttonAddTask.setOnClickListener {
            val taskName = editTextTaskName.text.toString()
            val priority = spinnerPriority.selectedItem.toString()
            val selectedId = radioGroupPriority.checkedRadioButtonId
            val selectedRadioButton = view.findViewById<RadioButton>(selectedId)
            val level = selectedRadioButton.text.toString()

            listener.onTaskAdded(taskName, priority, level)
            dismiss()
        }

        return view
    }
}
