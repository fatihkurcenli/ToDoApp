package com.autumnsun.todoapp.ui.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.autumnsun.todoapp.databinding.ActivityMainBinding
import com.autumnsun.todoapp.db.TaskRepository
import com.autumnsun.todoapp.model.Task
import com.autumnsun.todoapp.ui.adapter.TaskAdapter
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskRepository: TaskRepository
    private lateinit var taskList: ArrayList<Task>
    private lateinit var adapter: TaskAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar(binding.mainToolbar)
        supportActionBar?.title = "TODO-List"

        taskRepository = TaskRepository(this)
        taskList = taskRepository.getAllTask()

        binding.taskRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = TaskAdapter(this, taskList)
        binding.taskRecyclerview.adapter = adapter
        binding.taskRecyclerview.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )

        binding.addTaskFloatingButton.setOnClickListener {
            try {
                startActivity(Intent(this@MainActivity, TaskActivity::class.java))
            } catch (e: Exception) {
                Log.d("mainAcitivity", e.toString())
            }

        }
    }

    override fun onResume() {
        super.onResume()
        taskList = taskRepository.getAllTask()
        adapter.updateList(taskList)
    }
}


/*
class MainActivity : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences
    private lateinit var prefs: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    companion object {
        val PREFERENCE_FILE_KEY = "my_pref_key"
        val EDT_KEY = "edt_key"
        val CHK_KEY = "chk_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPref = getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
        prefs = getPreferences(Context.MODE_PRIVATE)
        readValue()
        binding.editButton.setOnClickListener {
            saveEdtValue()
            saveCheckBoxValue()
        }

    }

    private fun saveEdtValue() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.apply {
            putString(EDT_KEY, binding.editTextView.text.toString())
            apply()
        }
    }

    private fun saveCheckBoxValue() {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.apply {
            putBoolean(CHK_KEY, binding.checkBox.isChecked)
            apply()
        }
    }


    private fun readValue() {
        val value = sharedPref.getString(EDT_KEY, "none")
        binding.displayText.text = value
        val chkValue = prefs.getBoolean(CHK_KEY, false)
        binding.checkBox.isChecked = chkValue
    }
} */