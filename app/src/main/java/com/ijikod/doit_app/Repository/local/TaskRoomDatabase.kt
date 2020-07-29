package com.ijikod.doit_app.Repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ijikod.doit_app.Repository.Module.Task

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao


    companion object {
        @Volatile
        private var INSTANCE : TaskRoomDatabase? = null

        fun getDatabase(context: Context) : TaskRoomDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance !=  null){
                return tempInstance
            }

            synchronized(this){
                val instance =
                    Room.databaseBuilder(context.applicationContext, TaskRoomDatabase::class.java, "DoIt_database").build()
                INSTANCE = instance
                return instance
            }

        }
    }


}