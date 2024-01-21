package net.maystudios.mayide.Compiler.Parser;

import java.util.Map;
import java.util.Set;

public interface Parser {

    String parse(String input);

    Map<String, Set<String>> convertToMap(String input);
}
