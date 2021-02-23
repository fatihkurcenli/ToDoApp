package com.autumnsun.todoapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.autumnsun.todoapp.databinding.ActivityMainBinding

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
}