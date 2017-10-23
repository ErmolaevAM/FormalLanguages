package taskOne;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

    public UniversalAutoma(String path) {
        readAutomaFromJsonFile(path);
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
                    resultList.add(number.toString());
                    number.delete(0, number.length());
                    currentState = "1";
                }
            }
        }

        if (!"".equals(number.toString())) {
            resultList.add(number.toString());
            number.delete(0, number.length());
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
        return "";
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

}
