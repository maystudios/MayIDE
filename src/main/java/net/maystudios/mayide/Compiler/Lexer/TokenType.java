package net.maystudios.mayide.Compiler.Lexer;

public enum TokenType {
    WHITESPACE("\\s+"),
    ASSIGNMENT_OP("="),
    INCREMENT_OP("\\+"),
    DECREMENT_OP("-"),
    MULTIPLICATION_OP("\\*"),
    DIVISION_OP("/"),
    MODULO_OP("%"),
    EXPONENT_OP("\\^"),
    LEFT_PARENTHESIS("\\("),
    RIGHT_PARENTHESIS("\\)"),

    MEMORY_ACCESS_OP("\\rd"), // must be higher than IDENTIFIER
    MEMORY_ACCESS_OP2("\\wr"),
    MEMORY_ACCESS_OP3("\\fetch"),

    // Place IDENTIFIER after the MEMORY_ACCESS_OP
    IDENTIFIER("[a-zA-Z_][a-zA-Z0-9_]*"),
    SEMICOLON(";"),
    INTEGER("\\d+"),
    UNKNOWN(".");

    public final String pattern;

    TokenType(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return this.name();
    }
}