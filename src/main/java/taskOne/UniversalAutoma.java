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
                String signal = ((JSONObject)toStatesArray.get(j)).get("signal").toString();
                List<String> newSignalsList = new ArrayList<String>();
                JSONArray newSignalsArray = (JSONArray) ((JSONObject)toStatesArray.get(j)).get("newSignal");
                for (int k = 0; k < newSignalsArray.size(); k++) {
                    newSignalsList.add(String.valueOf(newSignalsArray.get(k)));
                }
                tmpMap.put(signal, newSignalsList);
            }
            map.put(String.valueOf(tmp.get("from")), tmpMap);
        }
        return map;
    }

    private boolean start(String word) {
        boolean flag = false;
        if (enteredStringValidation(word) == false) {
            System.out.println("Entered string contains invalid characters.");
            return flag;
        }
        //.....
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
