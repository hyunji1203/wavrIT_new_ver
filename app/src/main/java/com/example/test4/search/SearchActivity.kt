package com.example.test4.search

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.example.test4.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var filledTextField : TextInputLayout = findViewById(R.id.filledTextField)

        val textInputLayout = TextInputLayout(this)
        val editText = TextInputEditText(textInputLayout.context)

        // Get input text
        val inputText = filledTextField.editText?.text.toString()

        filledTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
            // Respond to input text change
        }
    }
}