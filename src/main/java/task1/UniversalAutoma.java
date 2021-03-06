package task1;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.LexemTypes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Александр on 21.09.2017.
 */
public class UniversalAutoma {

    private Map<String, Map<String, List<String>>> moveFunctions;
    private Set<String> alphabet;
    private Set<String> startStates;
    private Set<String> setOfFinalStates;

    private LexemTypes type;

    public UniversalAutoma(String path, LexemTypes typeOfLexem) {
        readAutomaFromJsonFile(path);
        type = typeOfLexem;
    }

    private void readAutomaFromJsonFile(String pathToJson) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(pathToJson));
            startStates = convertJsonArrayToSet("startStates", object);
            alphabet = convertJsonArrayToSet("alphabet", object);
            setOfFinalStates = convertJsonArrayToSet("setOfFinalStates", object);
            moveFunctions = readMoveFunctions(object);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Set<String> convertJsonArrayToSet(String key, JSONObject object) {
        Set<String> set = new HashSet<String>();
        JSONArray array = (JSONArray) object.get(key);
        Iterator iterator = array.iterator();
        String tmp = "";
        while (iterator.hasNext()) {
            tmp = iterator.next().toString();
            set.add(tmp);
        }
        return set;
    }

    private Map<String, Map<String, List<String>>> readMoveFunctions(JSONObject object) {
        Map<String, Map<String, List<String>>> map = new HashMap<String, Map<String, List<String>>>();
        JSONArray array = (JSONArray) object.get("moveFunctions");
        for (int i = 0; i < array.size(); i++) {
            JSONObject tmp = (JSONObject) array.get(i);
            Map<String, List<String>> tmpMap = new HashMap<String, List<String>>();
            JSONArray toStatesArray = (JSONArray) tmp.get("to");
            for (int j = 0; j < toStatesArray.size(); j++) {
                String signal = ((JSONObject) toStatesArray.get(j)).get("signal").toString();
                List<String> newSignalsList = new ArrayList<String>();
                JSONArray newSignalsArray = (JSONArray) ((JSONObject) toStatesArray.get(j)).get("newSignal");
                for (int k = 0; k < newSignalsArray.size(); k++) {
                    newSignalsList.add(String.valueOf(newSignalsArray.get(k)));
                }
                tmpMap.put(signal, newSignalsList);
            }
            map.put(String.valueOf(tmp.get("from")), tmpMap);
        }
        return map;
    }

    public LexemTypes getType() {
        return type;
    }

    /*-----LOGIC-----*/

    public boolean start(String word) {
        boolean flag = false;
        if (!enteredStringValidation(word)) {
            System.out.println("Entered string contains invalid characters.");
            return flag;
        }
        List<String> listOfCurrentStates = new ArrayList<String>();
        listOfCurrentStates.addAll(startStates);
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            List<String> newCurrentStates = new ArrayList<String>();
            for (int j = 0; j < listOfCurrentStates.size(); j++) {
                String currentState = listOfCurrentStates.get(j);
                List<String> newStates = moveFunctions.get(currentState).get(String.valueOf(charArray[i]));
                listOfCurrentStates.set(j, newStates.get(0));
                int index = 1;
                while (index < newStates.size()) {
                    newCurrentStates.add(newStates.get(index));
                    index++;
                }
            }
            listOfCurrentStates.addAll(newCurrentStates);
        }
        if (isCurrentStateFinal(listOfCurrentStates)) {
            flag = true;
        }
        return flag;
    }

    private List<String> taskTwoStart(String word, int index) {
        String subString = word.substring(index - 1); //находим подстроку, с которой будем запускать автомат
        List<String> resultList = new ArrayList<>();
        String currentState = getOneCurrentState();
        char[] chars = subString.toCharArray();
        int length = subString.toCharArray().length;

        StringBuilder number = new StringBuilder();

        //magic
        for (int i = 0; i < length; i++) {
            String tmp = "";
            if (!"".equals(translateSignals(chars[i]))) {
                tmp = moveFunctions.get(currentState).get(translateSignals(chars[i])).get(0);
                if (!"tmp".equals(tmp)) {
                    number.append(chars[i]);
                    currentState = tmp;
                } else if (setOfFinalStates.contains(currentState)) {
                    if (!"".equals(number.toString())) {
                        resultList.add(number.toString());
                        number.delete(0, number.length());
                        currentState = "1";
                        i--;
                    }
                }
            } else {
                if (!"".equals(number.toString())) {
                    cleanNumber(number);
                    resultList.add(number.toString());
                    number.delete(0, number.length());
                    currentState = "1";
                }
            }
        }

        if (setOfFinalStates.contains(currentState)) {
            if (!"".equals(number.toString())) {
                resultList.add(number.toString());
                number.delete(0, number.length());
            }
        }

        return resultList;
    }

    private boolean enteredStringValidation(String entered) {
        for (Character elem : entered.toCharArray()) {
            if (!alphabet.contains(String.valueOf(elem))) {
                return false;
            }
        }
        return true;
    }

    private boolean isCurrentStateFinal(List<String> listOfCurrentStates) {
        for (String elem : listOfCurrentStates) {
            if (setOfFinalStates.contains(elem)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCurrentStateFinal(String state) {
        return setOfFinalStates.contains(state);
    }

    private String translateSignals(Character signal) {
        if ('e' == signal || 'E' == signal) {
            return "E";
        }
        if ('.' == signal) {
            return "D";
        }
        if (Character.isDigit(signal)) {
            return "N";
        }
        if ('+' == signal || '-' == signal) {
            return "S";
        }
        if (Character.isLetter(signal)) {
            return "L";
        }
        return "";
    }

    private void cleanNumber(StringBuilder number) {
        if (number.toString().toCharArray()[number.length() - 1] == 'e' || number.toString().toCharArray()[number.length() - 1] == 'E') {
            while (number.toString().toCharArray()[number.length() - 1] == 'e' || number.toString().toCharArray()[number.length() - 1] == 'E') {
                number.delete(number.length() - 1, number.length());
            }
        }
    }

    private String getOneCurrentState() {
        String currentState = "";
        for (String item : startStates) {
            currentState = item;
        }
        return currentState;
    }

    public void printAllNumbers(String word, int index) {
        List<String> numbers = taskTwoStart(word, index);
        for (String item : numbers) {
            if (!item.isEmpty()) {
                System.out.println(item);
            }
        }
    }

    public void returnMaxNumberLength(String word, int index) {
        List<String> numbers = taskTwoStart(word, index);
        String maxWord = "";
        int maxLength = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).length() > maxLength) {
                maxWord = numbers.get(i);
                maxLength = maxWord.length();
            }
        }
        System.out.println("<" + maxWord + ";" + maxLength + ">");
    }

    /*-----task three methods-----*/

    public int findLexemsVar2(String text, int index) {
        char[] textInChars = text.toCharArray();
        String currentState = getOneCurrentState();
        int textLength = textInChars.length;

        StringBuilder word = new StringBuilder();
        StringBuilder finalWord = new StringBuilder();

        for (int i = index; i < textLength; i++) {
            String tmp = "";
            String symbol = String.valueOf(textInChars[i]);
            if (textInChars[i] == '\\') {
                symbol += textInChars[i + 1];
                i++;
            }
            if (alphabet.contains(symbol)) {
                tmp = moveFunctions.get(currentState).get(translator(symbol)).get(0);
                if (!"tmp".equals(tmp)) {
                    word.append(textInChars[i]);
                    if (isCurrentStateFinal(tmp)) {
                        finalWord = word;
                    }
                    currentState = tmp;
                } else {
                    if (isCurrentStateFinal(currentState)) {
                        finalWord = word;
                    }
                    return finalWord.length();
                }
            } else {
                if (isCurrentStateFinal(currentState)) {
                    finalWord = word;
                }
                return finalWord.length();
            }
        }
        return finalWord.length();
    } //work for AS.json, COL.json,


    private String translator(String elem) {
        if (type == LexemTypes.AS || type == LexemTypes.COL || type == LexemTypes.KW ||
                type == LexemTypes.LB || type == LexemTypes.LC || type == LexemTypes.LOG ||
                type == LexemTypes.LS || type == LexemTypes.RB || type == LexemTypes.RC ||
                type == LexemTypes.RS || type == LexemTypes.OP)
            return String.valueOf(elem);
        if (type == LexemTypes.NIL) {
            if ("n".equals(elem) || "N".equals(elem)) return "n";
            if ("i".equals(elem) || "I".equals(elem)) return "i";
            if ("l".equals(elem) || "L".equals(elem)) return "l";
        }
        if (type == LexemTypes.ID) {
            if (Character.isDigit(elem.toCharArray()[0])) return "N";
            if (Character.isLetter(elem.toCharArray()[0])) return "L";
            return "_";
        }
        if (type == LexemTypes.IN) {
            if (Character.isDigit(elem.toCharArray()[0])) return "N";
            if ("-".equals(elem) || "+".equals(elem)) return "S";
            return null;
        }
        if (type == LexemTypes.RN) {
            if (Character.isDigit(elem.toCharArray()[0])) return "N";
            if ("e".equals(elem) || "E".equals(elem)) return "E";
            if ("+".equals(elem) || "-".equals(elem)) return "S";
            if (".".equals(elem)) return "D";
        }
        if (type == LexemTypes.COM) {
            if ("(".equals(elem)) return "(";
            if (")".equals(elem)) return ")";
            if ("*".equals(elem)) return "*";
            return "content";
        }
        if (type == LexemTypes.WS) {
            if (" ".equals(elem)) return "space";
            if ("\n".equals(elem)) return "space";
            if ("\t".equals(elem)) return "space";
        }
        return null;
    }


    @Override
    public String toString() {
        return "UniversalAutoma{" +
                "type=" + type +
                '}';
    }
}
