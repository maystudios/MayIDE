package net.maystudios.mayide.Compiler.Parser;

import net.maystudios.mayide.Lib.Lib;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RegisterParser implements Parser {

    public static final Map<String, Integer> B_BUS_MAP = new HashMap<>();
    public static final Map<String, Integer> C_BUS_MAP = new HashMap<>();

    static {
        // Initialize B-Bus map
        B_BUS_MAP.put("MDR", 0);
        B_BUS_MAP.put("PC", 1);
        B_BUS_MAP.put("MBR", 2);
        B_BUS_MAP.put("MBRU", 3);
        B_BUS_MAP.put("SP", 4);
        B_BUS_MAP.put("LV", 5);
        B_BUS_MAP.put("CPP", 6);
        B_BUS_MAP.put("TOS", 7);
        B_BUS_MAP.put("OPC", 8);

        // Initialize C-Bus map
        C_BUS_MAP.put("MAR", 0);
        C_BUS_MAP.put("MDR", 1);
        C_BUS_MAP.put("PC", 2);
        C_BUS_MAP.put("SP", 3);
        C_BUS_MAP.put("LV", 4);
        C_BUS_MAP.put("CPP", 5);
        C_BUS_MAP.put("TOS", 6);
        C_BUS_MAP.put("OPC", 7);
        C_BUS_MAP.put("H", 8);
    }

    public static Map<String, Set<String>> parseRegisters(String input) {
        String[] parts = input.split(";"); // Split by semicolon to separate instructions
        Set<String> setRegisters = new HashSet<>();
        Set<String> usedRegisters = new HashSet<>();

        for (String part : parts) {
            String[] assignments = part.split("="); // Split by "=" to separate assignments
            for (int i = 0; i < assignments.length - 1; i++) {
                // Process for set registers
                String setPart = assignments[i].trim();
                if (C_BUS_MAP.containsKey(setPart)) {
                    setRegisters.add(setPart);
                }
            }

            // Process for used registers
            String[] tokens = assignments[assignments.length - 1].split("[\\s+-]+"); // Split by whitespace, plus, and
            // minus
            for (String token : tokens) {
                if (B_BUS_MAP.containsKey(token)) {
                    usedRegisters.add(token);
                }
            }
        }

        Map<String, Set<String>> result = new HashMap<>();
        result.put("Set", setRegisters);
        result.put("Used", usedRegisters);
        return result;
    }

    public String getSetRegisterBinary(String input) {
        Map<String, Set<String>> registers = parseRegisters(input);
        Set<String> setRegisters = registers.get("Set");

        String binary = "000000000";
        for (String setRegister : setRegisters) {
            binary = Lib.setBinaryOneAtIndex(binary, C_BUS_MAP.get(setRegister));
        }

        return binary;
    }

    public String getUsedRegisterBinary(String input) {
        Map<String, Set<String>> registers = parseRegisters(input);
        Set<String> usedRegisters = registers.get("Used");

        if (usedRegisters.size() > 1) {
            throw new IllegalArgumentException("More than one register used in instruction");
        }

        return Lib.IntToBinary(!usedRegisters.isEmpty() ? B_BUS_MAP.get(usedRegisters.iterator().next()) : 0, 4);
    }

    public static void main(String[] args) {
        String input1 = "MAR = SP = SP – 1; rd";
        System.out.println("Input 1: " + parseRegisters(input1));

        RegisterParser registerParser = new RegisterParser();
        System.out.println("Used register binary: " + registerParser.getUsedRegisterBinary(input1));
        System.out.println("Set register binary: " + registerParser.getSetRegisterBinary(input1));
    }

    @Override
    public String parse(String input) {
        return null;
    }

    @Override
    public Map<String, Set<String>> convertToMap(String input) {
        return null;
    }
}
