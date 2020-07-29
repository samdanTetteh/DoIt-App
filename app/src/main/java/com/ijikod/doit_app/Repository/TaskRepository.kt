package com.ijikod.doit_app.Repository

import androidx.lifecycle.LiveData
import com.ijikod.doit_app.Repository.Module.Task
import com.ijikod.doit_app.Repository.local.TaskDao

class TaskRepository(val taskDao : TaskDao) {


    val allTasks : LiveData<List<Task>> = taskDao.getTasks()

    suspend fun insert(task: Task){
        taskDao.insertTask(task)
    }

}