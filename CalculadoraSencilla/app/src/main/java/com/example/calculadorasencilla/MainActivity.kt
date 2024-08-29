package com.example.calculadorasencilla

import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadorasencilla.analizadores.Lexer
import com.example.calculadorasencilla.analizadores.*;
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream
import java.io.PrintStream
import java.io.StringReader

class MainActivity : AppCompatActivity() {

    private lateinit var inputField: EditText
    private lateinit var calculateButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputField = findViewById(R.id.inputField)
        calculateButton = findViewById(R.id.calculateButton)

        calculateButton.setOnClickListener {
            val expression = inputField.text.toString()
            try {
                val parserWrapper = ParserWrapper(expression)
                val success = parserWrapper.parse()

                if (success) {
                    val result = parserWrapper.result
                    if (result != null) {
                        showResultDialog("La respuesta es: $result")
                    } else {
                        showResultDialog("No se obtuvo resultado")
                    }
                } else {
                    showResultDialog("Error al analizar la expresión")
                }

            } catch (e: Exception) {
                e.printStackTrace()
                showResultDialog("Error en la expresión")
                Log.e("ParserError", "Se produjo una excepción: ${e.message}")
            }
        }
    }

    private fun showResultDialog(result: String) {
        AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(result)
            .setPositiveButton("OK", null)
            .show()
    }
}