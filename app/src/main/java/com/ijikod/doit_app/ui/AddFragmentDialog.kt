package com.ijikod.doit_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.ijikod.doit_app.R
import com.ijikod.doit_app.Repository.Module.Task
import com.ijikod.doit_app.databinding.FragmentAddDialogBinding
import com.ijikod.doit_app.vm.SharedTaskViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragmentDialog.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragmentDialog : DialogFragment() {


    lateinit var viewModel : SharedTaskViewModel
    lateinit var titleText : TextInputEditText
    lateinit var cancelBtn : MaterialButton
    lateinit var saveBtn : MaterialButton

    private lateinit var task: Task

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedTaskViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentAddDialogBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_add_dialog, container, false)
        val view = binding.root
        binding.vm = viewModel
        titleText = view.findViewById(R.id.task_edit_txt)
        cancelBtn = view.findViewById(R.id.cancel_btn)
        saveBtn = view.findViewById(R.id.save_btn)

        initPageEvents()

        return  view
    }


    private fun initPageEvents(){
        cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }

        saveBtn.setOnClickListener {
            task = Task(title = titleText.text.toString(), isCompleted = false)
            viewModel.saveTask(task)
            dialog?.dismiss()
        }
    }


    companion object {
        fun dialogInstance() : AddFragmentDialog{
            val dialog = AddFragmentDialog()
            return dialog
        }
    }

}