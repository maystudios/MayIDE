package net.maystudios.mayide.Compiler.Theme;

import net.maystudios.mayide.Compiler.Lexer.TokenType;

import java.util.HashMap;
import java.util.Map;

public class ThemeMay implements Theme{

    private Map<String, String> colors = new HashMap<>();

    public ThemeMay() {
        super();

        colors.put(TokenType.IDENTIFIER.toString(), "purple");
        colors.put(TokenType.INTEGER.toString(), "blue");

        colors.put(TokenType.ASSIGNMENT_OP.toString(), "red");

        colors.put(TokenType.INCREMENT_OP.toString(), "green");
        colors.put(TokenType.DECREMENT_OP.toString(), "green");
        colors.put(TokenType.MULTIPLICATION_OP.toString(), "green");
        colors.put(TokenType.DIVISION_OP.toString(), "green");
        colors.put(TokenType.MODULO_OP.toString(), "green");
        colors.put(TokenType.EXPONENT_OP.toString(), "green");

        colors.put(TokenType.LEFT_PARENTHESIS.toString(), "orange");
        colors.put(TokenType.RIGHT_PARENTHESIS.toString(), "orange");

        colors.put(TokenType.SEMICOLON.toString(), "purple");


        // TODO: Add all token types
        // Set default color to black
        for (TokenType token : TokenType.values()) {
            if (!colors.containsKey(token.toString())) {
                colors.put(token.toString(), "black");
            }
        }
    }

    @Override
    public String getColorForCode(String code) {
        return null;
    }

    @Override
    public String getColorForTokenType(String token) {
        return colors.get(token);
    }
}
