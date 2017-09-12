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
 * Created by Александр on 06.09.2017.
 */
public class UniversalDeterministicAutomaton {

    private Map<String, Map<String, String>> moveFunction;
    private Set<String> alphabet;
    private String startState;
    private Set<String> setOfFinalStates;

    public UniversalDeterministicAutomaton(String path) {
        parseDataFromJsonFile(path);
    }

    private void parseDataFromJsonFile(String path) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(path));
            startState = String.valueOf(object.get("startState"));
            setOfFinalStates = convertJsonArrayToSet("setOfFinalStates", object);
            alphabet = convertJsonArrayToSet("alphabet", object);
            moveFunction = readMoveFunctions(object);

            /*for (Map.Entry<String, Map<String, String>> item : moveFunction.entrySet()) {
                System.out.println(item.getKey());
                System.out.println(item.getValue());
                System.out.println("-------");
            }*/

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
        if (enteredStringValidation(word)) {
            System.out.println("The entered string contains invalid characters.");
            return false;
        }
        String currentState = startState;
        System.out.println("Current state: "+currentState);
        for (Character item : word.toCharArray()) {
            currentState = moveFunction.get(currentState).get(String.valueOf(item));
            System.out.println("Current state: "+currentState);
        }
        if (!isCurrentStateFinal(currentState)) {
            System.out.println("Current state isn't final state.");
            return false;
        } else {
            System.out.println("Congratulations!");
            return true;
        }
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
