package com.ijikod.doit_app.Repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ijikod.doit_app.Repository.Module.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table WHERE isCompleted = 0 ORDER BY id DESC")
    fun getTasks() : LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Query("DELETE FROM task_table WHERE id = :taskID")
    suspend fun deleteTask(taskID : Int)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()
}