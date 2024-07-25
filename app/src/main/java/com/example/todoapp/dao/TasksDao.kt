package com.example.todoapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.model.Task

@Dao
interface TasksDao {
    @Insert
    fun insertTask(task: Task)
    @Delete
    fun deleteTask(task: Task)
    @Update
    fun updateTask(task: Task)
    @Query("select * from tasks")
    fun getAllTasks():List<Task>

    @Query("select * from tasks where dateTime=:dateTime")
    fun getTasksByDate(dateTime:Long):List<Task>
}