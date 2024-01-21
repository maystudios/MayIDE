package net.maystudios.mayide.Compiler.Theme;

import net.maystudios.mayide.Compiler.Lexer.TokenType;

public interface Theme {

    String getColorForCode(String code);
    String getColorForTokenType(String token);

}
