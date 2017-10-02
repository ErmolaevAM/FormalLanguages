package utils;

import java.util.*;
import java.util.List;

/**
 * Created by Александр on 25.09.2017.
 */
public class JsonAutomaCreator {

    private Map<String, Map<String, List<String>>> moveFunctions; //Map<Текущее состояние, Map<Входной сигнал, List<Новые состояния>>>
    private Set<String> alphabet;
    private Set<String> startStates;
    private Set<String> setOfFinalStates;

    public JsonAutomaCreator() {
        alphabet = inputAlphabet();
        startStates = inputStartStates();
        setOfFinalStates = inputFinalStates();

    }

    private Set<String> inputAlphabet() {
        Set<String> tmpAlphabet = new HashSet<>();
        System.out.println("Input alphabet (for example: a, b, c,...):");
        Scanner input = new Scanner(System.in);
        String alphabetLine = input.nextLine();
        tmpAlphabet.addAll(Arrays.asList(alphabetLine.split(", ")));
        return tmpAlphabet;
    }

    private Set<String> inputStartStates() {
        Set<String> tmpStartStates = new HashSet<>();
        System.out.println("Input start states (for example: x, y, z,...):");
        Scanner input = new Scanner(System.in);
        String startStates = input.nextLine();
        tmpStartStates.addAll(Arrays.asList(startStates.split(", ")));
        return tmpStartStates;
    }

    private Set<String> inputFinalStates() {
        Set<String> tmpFinalStates = new HashSet<>();
        System.out.println("Input final states (for example: A, B, C,...):");
        Scanner input = new Scanner(System.in);
        String startStates = input.nextLine();
        tmpFinalStates.addAll(Arrays.asList(startStates.split(", ")));
        return tmpFinalStates;
    }

    private Map<String, Map<String, List<String>>> inputMoveFunctions() {
        Map<String, Map<String, List<String>>> tmpMoveFunctions = new HashMap<>();
        Scanner input = new Scanner(System.in);
        System.out.println("Input move functions: ");
        System.out.println("Input 'stop' to complete input...");
        String line = "";
        while (!"stop".equals(line)) {
            System.out.print("From: ");
            String from = input.nextLine();

        }
        return tmpMoveFunctions;
    }

    public static void main(String[] args) {
        JsonAutomaCreator jsonAutomaCreator = new JsonAutomaCreator();
    }
}
