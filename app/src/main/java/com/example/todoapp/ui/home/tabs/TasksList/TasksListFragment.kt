package com.example.todoapp.ui.home.tabs.TasksList

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todoapp.MyDatabase
import com.example.todoapp.databinding.TasksListFragmentBinding
import com.example.todoapp.model.Task
import com.example.todoapp.ui.home.UpdateActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener

class TasksListFragment:Fragment() {
    lateinit var viewBinding: TasksListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding= TasksListFragmentBinding.inflate(inflater,container,false)
        return viewBinding.root

    }

    override fun onStart() {
        super.onStart()
        loadTasks()
    }

     fun loadTasks() {
         context?.let {
             val tasks = MyDatabase.getInstance(it)
                 .tasksDao().getTasksByDate(selectedDate.timeInMillis)

             tasksAdapter.bindTasks(tasks.toMutableList())
         }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }
    val tasksAdapter=TasksListAdapter(null)

    val selectedDate=Calendar.getInstance()
    init {
        selectedDate.set(Calendar.HOUR_OF_DAY,0)
        selectedDate.set(Calendar.MINUTE,0)
        selectedDate.set(Calendar.SECOND,0)
        selectedDate.set(Calendar.MILLISECOND,0)
    }
    private fun initViews() {
        viewBinding.tasksRecyclerView.adapter= tasksAdapter
        viewBinding.calendarView.setSelectedDate(CalendarDay.today())
        tasksAdapter.onItemDeleteListener= TasksListAdapter.OnItemClickListener{position, task ->
            deleteTaskFromDataBase(task)
            tasksAdapter.taskDeleted(task)
        }

        viewBinding.calendarView.setOnDateChangedListener(OnDateSelectedListener{
            widget: MaterialCalendarView, date: CalendarDay, selected: Boolean ->
            if (selected) {
                selectedDate.set(Calendar.YEAR,date.year)
                selectedDate.set(Calendar.MONTH,date.month-1)
                selectedDate.set(Calendar.DAY_OF_MONTH,date.day)
                loadTasks()
            }
        })

        //implement update function
        tasksAdapter.onItemClickListener= TasksListAdapter.OnItemClickListener{position, task ->
            val intent= Intent(requireActivity(),UpdateActivity::class.java)
            intent.putExtra("task",task)
            startActivity(intent)

        }
        //change btn Done
        tasksAdapter.onButtonChangeClickListener=TasksListAdapter.OnItemClickListener{position, task ->
            MyDatabase.getInstance(requireContext()).tasksDao().updateTask(task)
        }
    }


    private fun deleteTaskFromDataBase(task: Task) {
        MyDatabase.getInstance(requireContext())
            .tasksDao().deleteTask(task)

    }
}