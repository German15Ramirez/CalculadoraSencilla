package com.example.calculadorasencilla

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadorasencilla.analizadores.Lexer
import com.example.calculadorasencilla.analizadores.Parser
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.io.StringReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputField = findViewById<TextInputEditText>(R.id.inputField)
        val resultField = findViewById<TextInputEditText>(R.id.resultField)
        val calculateButton = findViewById<MaterialButton>(R.id.calculateButton)

        calculateButton.setOnClickListener {
            val expression = inputField.text.toString()
            try {
                val reader = StringReader(expression)
                val lexer = Lexer(reader)
                val parser = Parser(lexer)

                // Realiza el análisis
                parser.parse()

                // Obtiene los resultados de la operación
                val resultados = parser.getListaResultados()
                if (resultados.isNotEmpty()) {
                    // Muestra el último resultado en el campo de texto
                    resultField.setText(resultados.last().toString())
                } else {
                    resultField.setText("Error en la expresión")
                }

            } catch (e: Exception) {
                e.printStackTrace()
                resultField.setText("Error en la expresión")
            }
        }
    }
}