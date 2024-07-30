package com.example.taskmanagementapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), AddTaskDialogFragment.AddTaskDialogListener {

    private lateinit var taskAdapter: TaskAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)
        taskAdapter = TaskAdapter()
        recyclerView.adapter = taskAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_task -> {
                showAddTaskDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showAddTaskDialog() {
        val dialog = AddTaskDialogFragment()
        dialog.show(supportFragmentManager, "AddTaskDialog")
    }

    override fun onTaskAdded(taskName: String, priority: String, level: String) {
        val task = Task(taskName, priority, level)
        taskAdapter.addTask(task)
    }
}
