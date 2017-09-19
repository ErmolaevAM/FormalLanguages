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
 * Created by ErmolaevAM on 19.09.2017.
 */
public class UniversalUndeterministicAutomation {
    private Map<String, Map<String, String>> moveFunction;
    private Set<String> alphabet;
    private Set<String> startState;
    private Set<String> setOfFinalStates;

    public UniversalUndeterministicAutomation(String path) {
        parseDataFromJsonFile(path);
    }

    private void parseDataFromJsonFile(String path) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(path));
            startState = convertJsonArrayToSet("startState", object);
            setOfFinalStates = convertJsonArrayToSet("setOfFinalStates", object);
            alphabet = convertJsonArrayToSet("alphabet", object);
            moveFunction = readMoveFunctions(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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

    private Map<String, Map<String, String>> readMoveFunctions(JSONObject object) {
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        JSONArray array = (JSONArray) object.get("moveFunctions");
        for (int i = 0; i < array.size(); i++) {
            JSONObject tmp = (JSONObject) array.get(i);
            Map<String, String> tmpMap = new HashMap<String, String>();
            JSONArray toArray = (JSONArray) tmp.get("to");
            for (int j = 0; j < toArray.size(); j++) {
                tmpMap.put(((JSONObject)toArray.get(j)).get("signal").toString(),(((JSONObject)toArray.get(j)).get("newState")).toString());
            }
            map.put(tmp.get("from").toString(), tmpMap);
        }
        return map;
    }

    public boolean start(String word) {
        boolean flag = false;
        if (enteredStringValidation(word)) {
            System.out.println("The entered string contains invalid characters.");
            return flag;
        }
        Object[] array = startState.toArray();
        for (Character item: word.toCharArray()) {
            for (int i = 0; i < array.length; i++) {
                array[i] = moveFunction.get(String.valueOf(array[i])).get(String.valueOf(item));
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (isCurrentStateFinal(String.valueOf(array[i]))) {
                flag = true;
            }
        }
        if (flag) {
            System.out.println("Congratulations!");
        } else {
            System.out.println("Current state isn't final state.");
        }
        return flag;
    }

    private boolean enteredStringValidation(String entered) {
        for (Character elem : entered.toCharArray()) {
            if (!alphabet.contains(elem)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCurrentStateFinal(String currentState) {
        return setOfFinalStates.contains(currentState);
    }
}
