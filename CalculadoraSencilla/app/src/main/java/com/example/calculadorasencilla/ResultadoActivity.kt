package com.example.calculadorasencilla

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {

    private lateinit var evResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        evResultado = findViewById(R.id.resultTextView)

        val resultado = intent.getStringExtra("resultado")

        if (resultado != null) {
            evResultado.text = resultado
        } else {
            evResultado.text = "No se recibió ningún resultado"
        }
    }
}