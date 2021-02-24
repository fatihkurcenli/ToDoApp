package com.autumnsun.todoapp.ui.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.autumnsun.todoapp.R
import com.autumnsun.todoapp.databinding.ActivityTaskBinding
import com.autumnsun.todoapp.db.TaskRepository
import com.autumnsun.todoapp.model.Task
import java.util.*

class TaskActivity : AppCompatActivity() {
    private lateinit var taskRepository: TaskRepository

    private lateinit var binding: ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar(binding.taskToolbar)
        actionBar?.title = "Add Todo"
        taskRepository = TaskRepository(this)

        if (intent.extras != null) {
            val task: Task = intent.extras?.getSerializable(MainActivity.EXTRA_TASK) as Task
            binding.taskNameEdt.setText(task.name)
            binding.endDateText.setText(task.date)
        }


        binding.confirmFab.setOnClickListener {
            if (intent.extras != null) {

                val task: Task = intent.extras?.getSerializable(MainActivity.EXTRA_TASK) as Task
                val rowId = taskRepository.updateTask(
                    Task(
                        task.id,
                        binding.taskNameEdt.text.toString(),
                        binding.endDateText.text.toString()
                    )
                )

                if (rowId > -1) {
                    Toast.makeText(this, "Güncellendi", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Güncelleme başarısız", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (!TextUtils.isEmpty(binding.taskNameEdt.text.toString())) {
                    val date: String =
                        if (binding.endDateText.text == null || binding.endDateText.text == getString(
                                R.string.end_date
                            )
                        ) "No end date"
                        else binding.endDateText.text.toString()
                    val rowId = taskRepository.insertTask(
                        Task(
                            name = binding.taskNameEdt.text.toString(),
                            date = date
                        )
                    )
                    if (rowId > -1) {
                        Toast.makeText(this, "Eklendi", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Ekleme başarısız", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this, "Task adı Boş geçilemez", Toast.LENGTH_SHORT).show()
                }
            }

        }
        binding.endDateLayout.setOnClickListener {
            getDateDialog()
        }

    }

    private fun getDateDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dialog = DatePickerDialog(this, { view, year, month, dayOfMonth ->
            val endDate = "$dayOfMonth.$month.$year" // 19.01.2018
            binding.endDateText.text = endDate
        }, year, month, day)

        dialog.datePicker.minDate = System.currentTimeMillis()
        dialog.show()

    }
}