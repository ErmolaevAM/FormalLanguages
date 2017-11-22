import task1.UniversalAutoma;
import utils.LexemTypes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Александр on 06.09.2017.
 */
public class Application {
    public static void main(String[] args) {
        /*UniversalAutoma asAutoma = new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\AS.json");
        asAutoma.findLexems("qwe = === 122 =====", LexemTypes.AS);*/ //work

        /*UniversalAutoma asAutoma = new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\COL.json");
        asAutoma.findLexems("qwe ; ;;; 122 ;;;;;", LexemTypes.COL);*/ //work

        /*UniversalAutoma asAutoma = new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\IN.json");
        asAutoma.findLexems("qwe 0 123 12345", LexemTypes.IN);*/ //work

        /*UniversalAutoma asAutoma = new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\ID.json");
        asAutoma.findLexems("qwe (**) (*____aq12w*) (*1a2a3a4a5*)", LexemTypes.ID);*/ //work

        /*UniversalAutoma asAutoma = new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RN.json");
        asAutoma.findLexems("12.42e1 47e12345 123.121e-008", LexemTypes.RN);*/ //work

        /*UniversalAutoma asAutoma = new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\COM.json");
        asAutoma.findLexems("(*aq12w*) (*1a2a3a4a5*)", LexemTypes.COM);*/ //work

        /*UniversalAutoma asAutoma = new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LOG.json");
        asAutoma.findLexems("123 123ADA false", LexemTypes.LOG);*/ //work

        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\EliseevaED\\Downloads\\FormalLanguages\\src\\main\\resources\\task3\\text.txt"))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                line = line + " ";
                text.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Integer, UniversalAutoma> map = new LinkedHashMap<>();
        map.put(4, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\KW.json", LexemTypes.KW));
        map.put(1, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\AS.json", LexemTypes.AS));
        map.put(2, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\COL.json", LexemTypes.COL));
        map.put(3, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\COM.json", LexemTypes.COM));
        map.put(6, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\ID.json", LexemTypes.ID));
        map.put(5, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\IN.json", LexemTypes.IN));

        map.put(7, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\LB.json", LexemTypes.LB));
        map.put(8, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\LC.json", LexemTypes.LC));
        map.put(9, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\LOG.json", LexemTypes.LOG));
        map.put(10, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\LS.json", LexemTypes.LS));
        map.put(11, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\NIL.json", LexemTypes.NIL));
        map.put(12, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\OP.json", LexemTypes.OP));
        map.put(13, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\RB.json", LexemTypes.RB));
        map.put(14, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\RC.json", LexemTypes.RC));
        map.put(15, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\RN.json", LexemTypes.RN));
        map.put(16, new UniversalAutoma("C:\\Users\\EliseevaED\\IdeaProjects\\FormalLanguages\\src\\main\\resources\\task3\\RS.json", LexemTypes.RS));

        function(text.toString(), map);
        System.out.println(text.toString());

    }

    public static void function(String text, Map<Integer, UniversalAutoma> automaMap) {

        for (int i = 0; i < text.length();) {
           // List<Integer> lenList = new ArrayList<>();
            int maxLen = 0;
            int priority = Integer.MAX_VALUE;
            for (Integer automa: automaMap.keySet()) {
                //lenList.add(automa.findLexems(text, i));
                int currentLen = automaMap.get(automa).findLexems(text, i);
                if(currentLen > maxLen && automa < priority){
                    maxLen = currentLen;
                    priority = automa;
                }

            }

            //int maxLen = 0;
            //int index = -1;

//            System.out.println(lenList);
//            for (int j = 0; j < lenList.size(); j++) {
//                if (lenList.get(j) > maxLen) {
//                    maxLen = lenList.get(j);
//                    index = j;
//                }
//            }


            if (maxLen == 0) {
                i++;
            } else {
                System.out.println("<"+automaMap.get(priority)+","+text.substring(i, i+maxLen)+">");
                i += maxLen;
            }
        }
    }
}






















