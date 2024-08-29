package com.example.calculadorasencilla.analizadores

class Token(val lexeme: String?, val line: Int, val column: Int) {
    // Constructor secundario: se puede crear un token sin lexema
    constructor(line: Int, column: Int) : this(null, line, column)

    // Propiedades generadas automáticamente
    // No es necesario definir getters explícitos
}