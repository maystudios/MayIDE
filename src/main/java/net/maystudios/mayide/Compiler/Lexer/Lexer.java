package net.maystudios.mayide.Compiler.Lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    public static List<String>[] lex(String input) {
        List<String>[] tokens = new ArrayList[]{new ArrayList<>(), new ArrayList<>()};
        String currentToken = "";
        for (char c : input.toCharArray()) {
            currentToken += c;
            boolean matchFound = false;
            for (TokenType tokenType : TokenType.values()) {
                Pattern tokenPattern = Pattern.compile("^" + tokenType.pattern + "$");
                Matcher matcher = tokenPattern.matcher(currentToken);
                if (matcher.find() && tokenType != TokenType.WHITESPACE) {
                    tokens[0].add(tokenType.name());
                    tokens[1].add(currentToken);
                    currentToken = "";
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound && !currentToken.trim().isEmpty()) {
                tokens[0].add(TokenType.UNKNOWN.name());
                currentToken = "";
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        String input = "MAR = SP = SP + 1; rd";
        List<String>[] tokens = lex(input);
        tokens[0].removeAll(List.of(TokenType.WHITESPACE.name(), TokenType.UNKNOWN.name()));
        String result = String.join(" ", tokens[0]);
        String result2 = String.join("", tokens[1]);
        System.out.println(result);
        System.out.println(result2);

        String priority = "";
        for (TokenType tokenType : TokenType.values()) {
             priority += tokenType.name() + " > ";
        }
        System.out.println(priority);
    }
}
