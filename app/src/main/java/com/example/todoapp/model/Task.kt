package com.example.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="tasks")
data class Task(

    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    @ColumnInfo
    var tittle:String?=null,
    @ColumnInfo






    var description: String? =null,
    @ColumnInfo
    var dateTime: Long? =null,
    @ColumnInfo
    var isDone:Boolean=false

) :Serializable                 //to enable send his data with the intent
//https://github.com/prolificinteractive/material-calendarview