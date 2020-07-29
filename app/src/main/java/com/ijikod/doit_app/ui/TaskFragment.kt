package com.ijikod.doit_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ijikod.doit_app.R
import com.ijikod.doit_app.Repository.Module.Task
import com.ijikod.doit_app.vm.SharedTaskViewModel


class TaskFragment : Fragment(), AddDialog {

    lateinit var fabBtn : FloatingActionButton
    lateinit var taskList : RecyclerView
    lateinit var viewModel: SharedTaskViewModel
    lateinit var adaptor : TaskAdapter
    lateinit var emptyText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedTaskViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_task, container, false)

        emptyText = view.findViewById(R.id.empty_text)
        taskList = view.findViewById(R.id.task_list)
        fabBtn = view.findViewById(R.id.add_task_btn)
        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) emptyText.visibility = View.GONE
            adaptor = TaskAdapter(it, this)
            taskList.adapter = adaptor
        })

        initPage()
        return view
    }



    private fun initPage(){
        fabBtn.setOnClickListener {
            showAddDialog(Task(title = "", isCompleted = false))
        }
    }

    override fun showAddDialog(task : Task?) {
        task?.let {
            viewModel.setSelectedTask(task)
        }
        val addTaskDialog  = AddFragmentDialog.dialogInstance()
        addTaskDialog.show(parentFragmentManager.beginTransaction(), "ADD")
    }
}