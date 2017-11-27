package task4;

import utils.LexemTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestParser {

    public static RegularExpressionParser parse(String filePath) {
        RegularExpressionParser obj = new RegularExpressionParser();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String lexemType = reader.readLine();
            System.out.println("Lexem type: " + lexemType);
            obj.setType(LexemTypes.valueOf(lexemType));

            int priority = Integer.parseInt(reader.readLine());
            System.out.println("Priority: " + priority);
            obj.setPriority(priority);

            String regex = reader.readLine();
            System.out.println("Regex: " + regex);
            Set<String> uniqueElemInRegex = uniqueElemInRegex(regex);

            obj.setAlphabet(uniqueElemInRegex);
            obj.addToStartState("0");

            findRegexChunks(regex);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> findRegexChunks(String regex) {
        List<String> chunks = new ArrayList<>();
        char[] chars = regex.toCharArray();
        int openBracketsCount = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\\') {
                String tmp = String.valueOf(chars[i]);
                tmp += chars[i + 1];
                i++;
                chunks.add(tmp);
            } else if (chars[i] == '(') {
                openBracketsCount++;
                while (openBracketsCount != 0) {

                }
            }
        }

        for (String elem : chunks) {
            System.out.println(elem);
        }

        return chunks;
    }

    private static Set<String> uniqueElemInRegex(String regex) {
        char[] chars = regex.toCharArray();
        Set<String> uniqueElements = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            String tmp = String.valueOf(chars[i]);
            if (chars[i] == '\\') {
                tmp += chars[i + 1];
                i++;
            }
            uniqueElements.add(tmp);
        }
        clean(uniqueElements);
        return uniqueElements;
    }

    private static void clean(Set<String> set) {
        Set<String> forDelete = new HashSet<>();
        for (String elem : set) {
            if ("|".equals(elem) || "(".equals(elem) || ")".equals(elem) || "*".equals(elem)) forDelete.add(elem);
        }
        set.removeAll(forDelete);
    }
}
