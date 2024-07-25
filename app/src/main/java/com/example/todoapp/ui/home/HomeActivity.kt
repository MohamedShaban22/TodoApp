package com.example.todoapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todoapp.R
import com.example.todoapp.databinding.ActivityHomeBinding
import com.example.todoapp.ui.home.tabs.SettingsFragment
import com.example.todoapp.ui.home.tabs.TasksList.TasksListFragment
import com.google.android.material.snackbar.Snackbar

class HomeActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityHomeBinding
    var tasksListFragmentRef:TasksListFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        viewBinding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.bottomNavigation.setOnItemSelectedListener{
            when(it.itemId){
                R.id.navigation_tasks_list->{
                    tasksListFragmentRef=TasksListFragment()
                    shownFragment(tasksListFragmentRef!!)
                }
                R.id.navigation_settings->{
                    shownFragment(SettingsFragment())
                }
            }

            return@setOnItemSelectedListener true
        }
        viewBinding.bottomNavigation.selectedItemId=R.id.navigation_tasks_list

        viewBinding.addTask.setOnClickListener{
            showBottomAddTask()
        }
    }

    private fun showBottomAddTask() {
        val addTaskFragment = AddTaskFragment()
        addTaskFragment.onAddTaskListener= AddTaskFragment.OnAddTaskListener {
            Snackbar.make(viewBinding.root, "Task Added Successfully", Snackbar.LENGTH_LONG)
                .show()
            //notify tasks list fragment
            tasksListFragmentRef?.loadTasks()

        }
        addTaskFragment.show(supportFragmentManager,"")
    }

    private fun shownFragment( fragment:Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container,fragment)
        .setCustomAnimations(R.anim.fad_in,R.anim.fade_out)
        .commit()
    }
}
