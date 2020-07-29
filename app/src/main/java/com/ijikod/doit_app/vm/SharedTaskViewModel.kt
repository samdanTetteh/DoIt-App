package com.ijikod.doit_app.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ijikod.doit_app.Repository.TaskRepository
import com.ijikod.doit_app.Repository.Module.Task
import com.ijikod.doit_app.Repository.local.TaskRoomDatabase
import kotlinx.coroutines.launch

class SharedTaskViewModel (application: Application) : AndroidViewModel(application) {

    private var repository : TaskRepository

    var  allTask : LiveData<List<Task>>

    val selectedTask : MutableLiveData<Task> = MutableLiveData<Task>()


    init {
        val taskDao = TaskRoomDatabase.getDatabase(application)
        repository = TaskRepository(taskDao = taskDao.taskDao())
        allTask = repository.allTasks
    }

     fun saveTask(task: Task){
        viewModelScope.launch {
            repository.insert(task)
        }
    }

    fun setSelectedTask(task: Task){
        selectedTask.value = task
    }


}