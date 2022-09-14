package com.example.sharedpreferences

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var nameText: EditText
    private lateinit var ageText: EditText
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText = findViewById(R.id.etName)
        ageText = findViewById(R.id.etAge)

        // Shared preference config ->
        sharedPreferences = getSharedPreferences("my_storage", MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle", "OnPause lifecycle launched")

        val name = nameText.text.toString()
        val age = ageText.text.toString()

        // Save the data in shared preference ->
        editor.apply {
            putString("sf_name",name)
            putString("sf_age", age)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()

        // Get data from shared preference ->
        val name = sharedPreferences.getString("sf_name", null)
        val age = sharedPreferences.getString("sf_age", null)

        // Auto-fill the data into the form input fields ->
        nameText.setText(name)
        ageText.setText(age)
    }
}