package com.autumnsun.todoapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.autumnsun.todoapp.R
import com.autumnsun.todoapp.model.Task

class TaskAdapter(var context: Context, var taskList: ArrayList<Task>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {
    private lateinit var onTaskComplateListener: OnTaskCompleteListener
    private lateinit var onTaskEditListener: OnTaskEditListener

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskName = view.findViewById<TextView>(R.id.item_task_name)
        val taskDate = view.findViewById<TextView>(R.id.item_date)
        val completeCheckBox = view.findViewById<CheckBox>(R.id.item_complete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {
        holder.taskName.text = taskList[position].name
        holder.taskDate.text = taskList[position].date
        holder.completeCheckBox.isChecked = false
        holder.completeCheckBox.setOnClickListener {
            onTaskComplateListener.let {
                it.onTaskComplete(taskList[position].id)
            }
        }
        holder.itemView.setOnCreateContextMenuListener { menu, v, menuInfo ->
            menu.add("Edit").setOnMenuItemClickListener {
                onTaskEditListener.let {
                    it.onEditTask(taskList[position])
                }
                return@setOnMenuItemClickListener true
            }
        }
    }

    fun setOnTaskCompleteListener(onTaskCompleteListener: OnTaskCompleteListener) {
        this.onTaskComplateListener = onTaskCompleteListener
    }

    fun setOnTaskEditListener(OnTaskEditListener: OnTaskEditListener) {
        this.onTaskEditListener = OnTaskEditListener
    }

    fun updateList(newList: ArrayList<Task>) {
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()
    }

    interface OnTaskCompleteListener {
        fun onTaskComplete(taskId: Int)
    }

    interface OnTaskEditListener {
        fun onEditTask(task: Task)
    }
}