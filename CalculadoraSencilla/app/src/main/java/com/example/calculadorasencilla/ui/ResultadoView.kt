package com.example.calculadorasencilla.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

class ResultadoView(context: Context, private val resultados: List<Double>?) : View(context) {

    private val paint = Paint().apply {
        color = 0xFF000000.toInt() // Color negro
        textSize = 40f // Tamaño del texto
        isAntiAlias = true // Suaviza los bordes del texto
    }

    override fun onDraw(canvas: Canvas) {
        if (canvas != null) {
            super.onDraw(canvas)
        }
        if (canvas != null) {
            // Dibuja los resultados en el lienzo
            resultados?.forEachIndexed { index, resultado ->
                canvas.drawText("Resultado ${index + 1}: $resultado", 10f, (index + 1) * 60f, paint)
            }
        }
    }
}