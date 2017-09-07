package taskOne;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Created by Александр on 06.09.2017.
 */
public class DeterministicAutomaton {

    private Set<String> setOfStates;
    private Set<String> inputSignals;
    private String startState;
    private Set<String> setOfFinalStates;
    private String[][] moveFunction;

    public DeterministicAutomaton(String pathToJsonFile) {
        parseDataFromJson(pathToJsonFile);
        moveFunction = new String[setOfStates.size()][inputSignals.size()];
        int index = 0;
        for (String item : setOfStates) {

        }
    }

    private void parseDataFromJson(String pathToJsonFile) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(new FileReader(pathToJsonFile));
            setOfStates = convertJsonArrayToHashSet("setOfStates", object);
            inputSignals = convertJsonArrayToHashSet("inputSignals", object);
            setOfFinalStates = convertJsonArrayToHashSet("setOfFinalStates", object);
            startState = String.valueOf(object.get("startState"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Set<String> convertJsonArrayToHashSet(String key, JSONObject object) {
        Set<String> set = new HashSet<String>();
        JSONArray jsonArray = (JSONArray) object.get(key);
        Iterator iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            String tmp = iterator.next().toString();
            set.add(tmp);
        }
        return set;
    }



    @Override
    public String toString() {
        return "DeterministicAutomaton{" +
                "setOfStates=" + setOfStates +
                ", inputSignals=" + inputSignals +
                ", startState='" + startState + '\'' +
                ", setOfFinalStates=" + setOfFinalStates +
                '}';
    }
}
