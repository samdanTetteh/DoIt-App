package com.ijikod.doit_app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.ijikod.doit_app.R
import com.ijikod.doit_app.Repository.Module.Task

class TaskAdapter(private val tasks : List<Task>) : RecyclerView.Adapter<TaskAdapter.TaskItemView>() {

    private lateinit var addTaskInterface : AddDialog

    constructor(task : List<Task>, addDialog: AddDialog ):this(task){
        addTaskInterface = addDialog
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return  TaskItemView(view)
    }

    override fun getItemCount()= tasks.size

    override fun onBindViewHolder(holder: TaskItemView, position: Int) {
        val task = tasks.get(position)
        with(holder){
            bind(task)
        }
    }



    inner class TaskItemView(view: View) : RecyclerView.ViewHolder(view){
        private val checkItem: CheckBox = view.findViewById<CheckBox>(R.id.task_item)

        fun bind(task : Task){
            checkItem.text = task.title
            checkItem.isChecked = task.isCompleted
            checkItem.setOnClickListener {
                addTaskInterface.showAddDialog(task)
            }
        }
    }
}



