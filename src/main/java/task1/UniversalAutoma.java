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
        if (number.toString().toCharArray()[number.length()-1]=='e' || number.toString().toCharArray()[number.length()-1]=='E') {
            while (number.toString().toCharArray()[number.length()-1]=='e' || number.toString().toCharArray()[number.length()-1]=='E') {
                number.delete(number.length()-1, number.length());
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
        String maxWord= "";
        int maxLength = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).length() > maxLength) {
                maxWord = numbers.get(i);
                maxLength = maxWord.length();
            }
        }
        System.out.println("<"+maxWord+";"+maxLength+">");
    }

    /*-----task three methods-----*/

    public List<String> findLexems(String text, LexemTypes type) {
        char[] textInChars = text.toCharArray();
        List<String> resultList = new ArrayList<>();
        String currentState = getOneCurrentState();
        int textLength = textInChars.length;

        StringBuilder word = new StringBuilder();
        StringBuilder finalWord = new StringBuilder();

        for (int i = 0; i < textLength; i++) {
            String tmp = "";
            if (!"".equals(textInChars[i])) {
                if (alphabet.contains(String.valueOf(textInChars[i]))) {
//                    tmp = moveFunctions.get(currentState).get(String.valueOf(textInChars[i])).get(0); //new state
                    tmp = moveFunctions.get(currentState).get(translator(textInChars[i], type)).get(0); //new state
                    if (!"tmp".equals(tmp)) { //if new state is not 'tmp'
                        word.append(textInChars[i]); //add one symbol to the current word
                        if (isCurrentStateFinal(currentState)) { //if current state is final
                            finalWord = word; //add current word to the final word
                        }
                        currentState = tmp; //change current state to the new non 'tmp' state
                    } else { //if new state is 'tmp'
                        resultList.add(finalWord.toString()); //save final word from last non 'tmp' state to the result list
                        word.delete(0, word.length()); //clear word
                        finalWord.delete(0, finalWord.length()); //clear final word
                        currentState = getOneCurrentState();
                    }
                } else if (!"".equals(finalWord.toString()) || !"".equals(word.toString())){ //if word/final word is not empty
                    currentState = save(currentState, word, finalWord, resultList);
                }
            } else { //if new symbol from text is empty string
                currentState = save(currentState, word, finalWord, resultList);
            }
        }

        //add last word if
        if (isCurrentStateFinal(currentState)) {
            finalWord = word;
            resultList.add(finalWord.toString());
            word.delete(0, word.length());
            finalWord.delete(0, finalWord.length());
        } else {
            resultList.add(word.toString());
            word.delete(0, word.length());
            finalWord.delete(0, finalWord.length());
        }

        int maxLength = 0;
        String wordWithMaxLength = "";

        for (String item : resultList) {
            if (maxLength < item.length()) {
                maxLength = item.length();
                wordWithMaxLength = item;
            }
        }

//        System.out.println("<"+wordWithMaxLength+", "+type+">");
        return resultList;
    } //work for AS.json, COL.json,

    private String save(String state, StringBuilder word, StringBuilder finalWord, List<String> resultList) {
        if (isCurrentStateFinal(state)) {
            finalWord = word;
        }
        resultList.add(finalWord.toString());
        word.delete(0, word.length());
        finalWord.delete(0, finalWord.length());
        return getOneCurrentState();
    }

    private String translator(char elem, LexemTypes type) {
        if (type == LexemTypes.AS || type == LexemTypes.COL || type == LexemTypes.KW ||
                type == LexemTypes.LB || type == LexemTypes.LC || type == LexemTypes.LOG ||
                type == LexemTypes.LS || type == LexemTypes.NIL || type == LexemTypes.RB ||
                type == LexemTypes.RC || type == LexemTypes.RS || type == LexemTypes.OP)
            return String.valueOf(elem);
        if (type == LexemTypes.ID) {
            if (Character.isDigit(elem)) return "N";
            if (Character.isLetter(elem)) return "L";
            return "_";
        }
        if (type == LexemTypes.IN) {
            if (Character.isDigit(elem)) return "N";
            return null;
        }
        if (type == LexemTypes.RN) {
            if (Character.isDigit(elem)) return "N";
            if (elem == 'e' || elem == 'E') return "E";
            if (elem == '+' || elem == '-') return "S";
            if (elem == '.') return "D";
        }
        if (type == LexemTypes.COM) {
            if (elem == '(') return "(";
            if (elem == ')') return ")";
            if (elem == '*') return "*";
            return "content";
        }
        return null;
    }

}
