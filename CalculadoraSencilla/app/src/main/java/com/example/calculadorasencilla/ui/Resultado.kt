package com.example.calculadorasencilla.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadorasencilla.R
import java.security.NoSuchAlgorithmException

class Resultado : AppCompatActivity() {

    private var resultados: List<Double>? = null // Lista de resultados de cálculos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textResultado)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Recibimos los datos de la ventana o actividad anterior
        recibirDatos()

        // Obtén el layout del activity y pásalo como contexto a un nuevo componente que mostrará los resultados
        val layout1 = findViewById<View>(R.id.textResultado) as ConstraintLayout
        val resultadoView = ResultadoView(this, this.resultados)
        layout1.addView(resultadoView)
    }

    // Método para recibir datos de la ventana anterior
    private fun recibirDatos() {
        val datos = intent.extras
        if (datos != null) {
            this.resultados = datos.getSerializable("resultados") as List<Double>?
        }
    }
}