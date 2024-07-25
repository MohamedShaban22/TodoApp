package com.example.todoapp.ui.home

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.MyDatabase

import com.example.todoapp.databinding.FragmentAddTaskBinding
import com.example.todoapp.model.Task
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddTaskFragment : BottomSheetDialogFragment() {
    lateinit var viewBinding: FragmentAddTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.addTaskBtn.setOnClickListener {
            createTask()
        }
        viewBinding.selectDateLayout.setOnClickListener {

            showDateDialoge()
        }
    }

    val calender = Calendar.getInstance()
    private fun showDateDialoge() {
        context?.let {
            val dialog = DatePickerDialog(it)
            dialog.setOnDateSetListener { datePicker, year, month, day ->

                viewBinding.dateTv.text = "$day-${month+1}-$year"
                calender.set(year, month, day)
                calender.set(Calendar.HOUR_OF_DAY,0)
                calender.set(Calendar.MINUTE,0)
                calender.set(Calendar.SECOND,0)
                calender.set(Calendar.MILLISECOND,0)

            }
            dialog.show()
        }
    }



    private fun valid(): Boolean {
        var valid = true
        if (viewBinding.titleEt.text.toString().isBlank()) {
            viewBinding.titleEtLayout.error = "Enter Title"
            valid = false
        } else {
            viewBinding.titleEtLayout.error = null

        }
        if (viewBinding.descriptionEt.text.toString().isBlank()) {
            viewBinding.descriptionEtLayout.error = "Enter Description"
            valid = false
        } else {
            viewBinding.descriptionEtLayout.error = null

        }
        if (viewBinding.dateTv.text.toString().isBlank()) {
            viewBinding.selectDateLayout.error = "Enter Date"
            valid = false
        } else {
            viewBinding.selectDateLayout.error = null

        }
        return valid
    }

    private fun createTask() {
        if (!valid()) {
            return
        }
        val task = Task(
            tittle = viewBinding.titleEt.text.toString(),
            description = viewBinding.descriptionEt.text.toString(),
            dateTime = calender.timeInMillis
            )
        MyDatabase.getInstance(requireContext())
            .tasksDao().insertTask(task)
        onAddTaskListener?.onTaskAdd()
        dismiss()
    }

var onAddTaskListener: OnAddTaskListener?=null
fun interface OnAddTaskListener{
    fun onTaskAdd()


}
}