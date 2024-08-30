package com.example.calculadorasencilla

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class ErroresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.errores_activity)

        val errors = intent.getStringArrayListExtra("errors")
        //val errorsTextView = findViewById<TextView>(R.id.tvErrors)

        val errorMessages = errors?.joinToString("\n") ?: "No hay errores para mostrar"
        //errorsTextView.text = errorMessages
    }
}