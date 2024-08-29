package com.example.calculadorasencilla.analizadores

import com.example.calculadorasencilla.analizadores.Lexer
import com.example.calculadorasencilla.analizadores.Parser
import java.io.StringReader
import java_cup.runtime.Symbol

class ParserWrapper(private val expression: String) {

    var result: Double? = null
        private set

    fun parse(): Boolean {
        return try {
            // Inicializa el lexer con la expresión
            val lexer = Lexer(StringReader(expression))
            // Inicializa el parser con el lexer
            val parser = Parser(lexer)
            // Analiza la expresión
            val parseResult = parser.parse()
            // Obtiene el valor del Symbol
            if (parseResult is Symbol) {
                result = parseResult.value as? Double
            } else {
                result = parseResult as? Double
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}