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

    private Symbol symbol(int type, String lexeme) {
        System.out.printf("Token tipo %d, lexeme %s, en linea %d, columna %d\n", type, lexeme == null ? "" : lexeme, yyline + 1, yycolumn + 1);
        return new Symbol(type, new Token(lexeme, yyline + 1, yycolumn + 1));
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

"+"               { return new Symbol(sym.SUMA, yyline+1, yycolumn+1, yytext()); }
"-"               { return new Symbol(sym.RESTA, yyline+1, yycolumn+1, yytext()); }
"*"               { return new Symbol(sym.MULTIPLICACION, yyline+1, yycolumn+1, yytext()); }
"/"               { return new Symbol(sym.DIVISION, yyline+1, yycolumn+1, yytext()); }
"("               { return new Symbol(sym.PAR_IZQ, yyline+1, yycolumn+1, yytext()); }
")"               { return new Symbol(sym.PAR_DER, yyline+1, yycolumn+1, yytext()); }

[0-9]+\.[0-9]+    { return new Symbol(sym.NUMERO, Double.parseDouble(yytext())); }
[0-9]+            { return new Symbol(sym.NUMERO, Integer.parseInt(yytext())); }

[ \t\n\r\f]+      { /* Ignorar espacios en blanco */ }
<<EOF>>           { return new Symbol(sym.EOF); }

[^]                  { System.err.println("Error léxico: caracter desconocido: " + yytext()); }