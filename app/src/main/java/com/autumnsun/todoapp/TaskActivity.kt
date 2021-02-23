package com.autumnsun.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.autumnsun.todoapp.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar(binding.taskToolbar)

        actionBar?.title = "Add Todo"
    }
}