package task4;

import utils.LexemTypes;

import java.util.*;

public class RegularExpressionParser {

    private LexemTypes type; //+

    private int priority; //+

    private Set<String> startStates;

    private Set<String> setOfFinalStates;

    private Set<String> alphabet; //+

    private Map<String, Map<String, List<String>>> moveFunctions;

    public RegularExpressionParser() {
        startStates = new HashSet<>();
        setOfFinalStates = new HashSet<>();
        alphabet = new HashSet<>();
        moveFunctions = new HashMap<>();
    }

    public LexemTypes getType() {
        return type;
    }

    public void setType(LexemTypes type) {
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    /*start states methods*/
    public Set<String> getStartStates() {
        return startStates;
    }

    public void setStartStates(Set<String> startStates) {
        this.startStates = startStates;
    }

    public void addToStartState(String oneMoreStartState) {
        this.startStates.add(oneMoreStartState);
    }

    /*final states methods*/
    public Set<String> getSetOfFinalStates() {
        return setOfFinalStates;
    }

    public void setSetOfFinalStates(Set<String> setOfFinalStates) {
        this.setOfFinalStates = setOfFinalStates;
    }

    public void addToFinalState(String oneMoreFinalState) {
        this.setOfFinalStates.add(oneMoreFinalState);
    }

    /*alphabet methods*/
    public Set<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Set<String> alphabet) {
        this.alphabet = alphabet;
    }

    public void addToAlphabet(String oneMoreSymbolToAlphabet) {
        this.alphabet.add(oneMoreSymbolToAlphabet);
    }

    /*move functions*/
    public Map<String, Map<String, List<String>>> getMoveFunctions() {
        return moveFunctions;
    }

    public void setMoveFunctions(Map<String, Map<String, List<String>>> moveFunctions) {
        this.moveFunctions = moveFunctions;
    }

    public void addToMoveFunctions(String key, String symbol, List<String> newStates) {
        if (moveFunctions.containsKey(key)) {
            if (moveFunctions.get(key).containsKey(symbol)) {
                for (String item : newStates) {
                    moveFunctions.get(key).get(symbol).add(item); //add new states if symbol exist
                }
            } else {
                moveFunctions.get(key).put(symbol, newStates); //put value<symbol, newStates> to map for key
            }
        } else {
            Map<String, List<String>> tmp = new HashMap<>();
            tmp.put(symbol, newStates);
            moveFunctions.put(key, tmp);
        }
    }
}
