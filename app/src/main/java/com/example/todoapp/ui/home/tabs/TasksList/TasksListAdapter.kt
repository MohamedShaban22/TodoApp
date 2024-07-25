package com.example.todoapp.ui.home.tabs.TasksList

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.databinding.TaskItemBinding
import com.example.todoapp.model.Task

class TasksListAdapter(var tasks:MutableList<Task>?):RecyclerView.Adapter<TasksListAdapter.ViewHolder>() {



    class ViewHolder(val itemBinding:TaskItemBinding):RecyclerView.ViewHolder(itemBinding.root){

        fun bind(task:Task){
            itemBinding.titleItem.text=task.tittle
            itemBinding.descriptionItem.text= task.description.toString()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TasksListAdapter.ViewHolder {
        val itemBinding=TaskItemBinding
            .inflate(LayoutInflater.from(parent.context)
                ,parent,false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int =tasks?.size?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks!![position])
        if (onItemDeleteListener!=null){
            holder.itemBinding.deleteView
                .setOnClickListener{
                    holder.itemBinding.swipeLayout.close(true)
                    onItemDeleteListener?.onItemClick(position, tasks!![position])
                }
        }

        //on Item click update
        holder.itemBinding.dragItem.setOnClickListener{
            onItemClickListener?.onItemClick(position,tasks!![position])
        }
        //btn done
        if(tasks!![position].isDone){
            holder.itemBinding.btnDone.text = "Done!"
            holder.itemBinding.btnDone.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.transpernt))
            holder.itemBinding.btnDone.setTextColor(Color.GREEN)
            holder.itemBinding.btnDone.setIconTintResource(R.color.transpernt)
            holder.itemBinding.titleItem.setTextColor(Color.GREEN)
            holder.itemBinding.verticalView.setBackgroundColor(Color.GREEN)

        }
        else{
            holder.itemBinding.btnDone.text = ""
            holder.itemBinding.btnDone.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.lightPrimary))
            holder.itemBinding.btnDone.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.lightPrimary))
            holder.itemBinding.btnDone.setIconTintResource(R.color.white)
            holder.itemBinding.verticalView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.lightPrimary))
            holder.itemBinding.titleItem.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.lightPrimary))
            }

        holder.itemBinding.btnDone.setOnClickListener {
            tasks!![position].isDone=!(tasks!![position].isDone)
            onButtonChangeClickListener?.onItemClick(position,tasks!![position])
            notifyItemChanged(position)
        }

    }

    fun bindTasks(tasks: MutableList<Task>) {
        this.tasks=tasks
        notifyDataSetChanged()
    }

    fun taskDeleted(task: Task) {
        tasks?.remove(task)
        notifyDataSetChanged()    }
    var onButtonChangeClickListener:OnItemClickListener?=null
    var onItemClickListener:OnItemClickListener?=null
    var onItemDeleteListener: OnItemClickListener?=null
    fun interface OnItemClickListener{
        fun onItemClick(position:Int,task:Task)
    }
}