package com.example.calculadorasencilla.analizadores;

import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.*;
import static com.example.calculadorasencilla.analizadores.sym.*;

%%

%public
%class Lexer
%cup
%cupdebug
%line
%column

%{

    private List<String> errorsList;

    private Symbol symbol(int type) {
        String lexeme = yytext();
        System.out.printf("Token tipo %d, lexeme %s, en linea %d, columna %d\n", type, lexeme == null ? "" : lexeme, yyline + 1, yycolumn + 1);
        return new Symbol(type, new Token(lexeme, yyline + 1, yycolumn + 1));
    }

    private Symbol symbol(int type, Object value) {
        String lexeme = yytext();
        System.out.printf("Token tipo %d, lexeme %s, en linea %d, columna %d\n", type, lexeme == null ? "" : lexeme, yyline + 1, yycolumn + 1);
        return new Symbol(type, value);
    }

    private void error(String lexeme) {
        System.out.printf("Error Lexico en el Texto: %s  linea %d,  columna %d. \n", lexeme, yyline + 1, yycolumn + 1);
        errorsList.add(String.format("Error Lexico en el Texto: %s  linea %d, columna %d. Corrige e intenta de nuevo.", lexeme, yyline + 1, yycolumn + 1));
    }

    public List<String> getErrorsList() {
        return errorsList;
    }

%}

%init{
    errorsList = new ArrayList<>();
%init}

%%

"+"               { return symbol(sym.SUMA); }
"-"               { return symbol(sym.RESTA); }
"*"               { return symbol(sym.MULTIPLICACION); }
"/"               { return symbol(sym.DIVISION); }
"("               { return symbol(sym.PAR_IZQ); }
")"               { return symbol(sym.PAR_DER); }

[0-9]+(\.[0-9]*)? { return symbol(sym.NUMERO, Double.parseDouble(yytext())); }

[ \t\n\r\f]+      { /* Ignorar espacios en blanco */ }
<<EOF>>           { return symbol(sym.EOF); }

[^]                  { System.err.println("Error léxico: caracter desconocido: " + yytext()); }