package com.example.calculadorasencilla.analizadores

import java.io.StringReader
import java_cup.runtime.Symbol

class ParserWrapper(private val expression: String) {

    var result: Double? = null
        private set
    val errors: List<String>
        get() = lexer.errorsList

    private val lexer = Lexer(StringReader(expression))

    fun parse(): Boolean {
        return try {
            val parser = Parser(lexer)
            val parseResult = parser.parse()

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