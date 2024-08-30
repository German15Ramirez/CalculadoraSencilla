package com.example.calculadorasencilla

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class ErrorItem(val linea: Int, val columna: Int, val mensaje: String)

class ErrorAdapter(private val errores: List<ErrorItem>) :
    RecyclerView.Adapter<ErrorAdapter.ErrorViewHolder>() {

    class ErrorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lineaTextView: TextView = view.findViewById(R.id.lineaTextView)
        val columnaTextView: TextView = view.findViewById(R.id.columnaTextView)
        val mensajeTextView: TextView = view.findViewById(R.id.mensajeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ErrorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_error, parent, false)
        return ErrorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ErrorViewHolder, position: Int) {
        val error = errores[position]
        holder.lineaTextView.text = "Línea: ${error.linea}"
        holder.columnaTextView.text = "Columna: ${error.columna}"
        holder.mensajeTextView.text = error.mensaje
    }

    override fun getItemCount() = errores.size
}