package net.maystudios.mayide.Compiler.Compiler;

import java.util.Map;

public interface Compiler {

    Map<String, String> compile(String[] files);

}
