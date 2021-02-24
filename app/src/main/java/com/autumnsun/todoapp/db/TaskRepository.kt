package com.autumnsun.todoapp.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.autumnsun.todoapp.model.Task

class TaskRepository(var context: Context) {

    private var mDBHelper: DBHelper = DBHelper.getInstance(context)


    fun getAllTask(): ArrayList<Task> {
        var list = ArrayList<Task>()
        val db = mDBHelper.readableDatabase
        val query =
            "SELECT ${DBHelper.KEY_ID}, ${DBHelper.KEY_NAME}, ${DBHelper.KEY_DATE} FROM ${DBHelper.TABLE_NAME}"
        val cursor: Cursor = db.rawQuery(query, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID))
                val name = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
                val date = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_DATE))
                val task = Task(id, name, date)
                list.add(task)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    fun insertTask(task: Task): Int {
        val db = mDBHelper.writableDatabase
        val values = ContentValues()
        values.apply {
            put(DBHelper.KEY_NAME, task.name)
            put(DBHelper.KEY_DATE, task.date)
        }
        var id = db.insert(DBHelper.TABLE_NAME, null, values)
        db.close()
        return id.toInt()
    }

    fun deleteTask() {

    }

    fun updateTask() {

    }
}