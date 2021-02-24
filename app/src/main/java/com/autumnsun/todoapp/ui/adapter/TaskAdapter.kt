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
    }

    fun updateList(newList: ArrayList<Task>) {
        taskList.clear()
        taskList.addAll(newList)
        notifyDataSetChanged()
    }
}