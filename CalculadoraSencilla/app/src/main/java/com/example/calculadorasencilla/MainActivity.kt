package com.example.calculadorasencilla

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadorasencilla.analizadores.*;
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private lateinit var inputField: EditText
    private lateinit var calculateButton: MaterialButton
    private lateinit var erroresButton: MaterialButton
    private var parserWrapper: ParserWrapper? = null
    private var canOpenErroresActivity = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputField = findViewById(R.id.inputField)
        calculateButton = findViewById(R.id.calculateButton)
        erroresButton = findViewById(R.id.showErrorsButton)

        calculateButton.setOnClickListener {
            val expression = inputField.text.toString()
            if (expression.isBlank()) {
                Toast.makeText(this, "Por favor, ingrese una operación", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                parserWrapper = ParserWrapper(expression)
                val success = parserWrapper!!.parse()

                if (parserWrapper!!.errors.isNotEmpty()) {
                    canOpenErroresActivity = true
                } else {
                    canOpenErroresActivity = true
                }

                if (parserWrapper!!.errors.isNotEmpty()) {
                    openErroresActivity()
                } else if (success) {
                    val result = parserWrapper!!.result
                    if (result != null) {
                        openResultadoActivity(result.toString())
                    } else {
                        openResultadoActivity("No se obtuvo resultado")
                    }
                } else {
                    openResultadoActivity("Error al analizar la expresión")
                }

            } catch (e: Exception) {
                e.printStackTrace()
                openResultadoActivity("Error en la expresión")
                Log.e("ParserError", "Se produjo una excepción: ${e.message}")
            }
        }

        erroresButton.setOnClickListener {
            if (canOpenErroresActivity) {
                openErroresActivity()
            } else {
                Toast.makeText(this, "No hay errores para mostrar", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openErroresActivity() {
        if (!canOpenErroresActivity) {
            Toast.makeText(this, "No hay errores para mostrar", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent(this, ErroresActivity::class.java)
        intent.putStringArrayListExtra("errors", ArrayList(parserWrapper!!.errors))
        startActivity(intent)
    }

    private fun openResultadoActivity(result: String) {
        val intent = Intent(this, ResultadoActivity::class.java)
        intent.putExtra("resultado", "La Respuesta de Esta Operación Matemática es:\n\n$result")
        startActivity(intent)
    }
}