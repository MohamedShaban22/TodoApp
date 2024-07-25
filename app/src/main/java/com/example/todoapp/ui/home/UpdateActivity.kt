package com.example.todoapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.MyDatabase

import com.example.todoapp.databinding.ActivityUpdateBinding
import com.example.todoapp.model.Task
import java.text.SimpleDateFormat
import java.util.Date

class UpdateActivity : AppCompatActivity() {
    lateinit var task: Task
    lateinit var viewBinding:ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding=ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.icBack.setOnClickListener {
            finish()
        }


            task = ((intent.getSerializableExtra("task")as? Task)!!)
            val timeConverted= task.dateTime?.let { convertLongToTime(it,) }

        viewBinding.TitleUpEt.setText(task.tittle)
            viewBinding.descriptionUpEt.setText(task.description)
            viewBinding.dateValueTv.setText(timeConverted)


        viewBinding.editBtn.setOnClickListener{
            updateData()
            finish()
        }
    }

    private fun updateData() {
        task.tittle=viewBinding.TitleUpEt.text.toString()
        task.description=viewBinding.descriptionUpEt.text.toString()
        MyDatabase.getInstance(this).tasksDao().updateTask(task)

    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("dd-MM-yyyy")
        return format.format(date)
    }

}