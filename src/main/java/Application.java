import task1.UniversalAutoma;
import utils.LexemTypes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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

        Map<Integer, UniversalAutoma> map = new LinkedHashMap<>();
        map.put(1, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\AS.json", LexemTypes.AS));
        map.put(2, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\COL.json", LexemTypes.COL));
        map.put(3, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\COM.json", LexemTypes.COM));
        map.put(4, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\ID.json", LexemTypes.ID));
        map.put(5, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\IN.json", LexemTypes.IN));
        map.put(6, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\KW.json", LexemTypes.KW));
        map.put(7, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LB.json", LexemTypes.LB));
        map.put(8, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LC.json", LexemTypes.LC));
        map.put(9, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LOG.json", LexemTypes.LOG));
        map.put(10, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LS.json", LexemTypes.LS));
        map.put(11, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\NIL.json", LexemTypes.NIL));
        map.put(12, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\OP.json", LexemTypes.OP));
        map.put(13, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RB.json", LexemTypes.RB));
        map.put(14, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RC.json", LexemTypes.RC));
        map.put(15, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RN.json", LexemTypes.RN));
        map.put(16, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RS.json", LexemTypes.RS));

        function("= == ; ;; (*asd123*) _asd11 __asd11 0 00 if then ( (( { {{ true false [ [[ nil NIL + - -+ ) )) } }} 12.3 12.4e17 ] ]]", map);

    }

    public static void function(String text, Map<Integer, UniversalAutoma> automaMap) {
        Map<String, LexemTypes> resultMap = new HashMap<>();

        for (UniversalAutoma automa : automaMap.values()) {
            List<String> lexems = automa.findLexems(text, automa.getType());
            String word = "";
            for (String elem : lexems) {
                if (elem.length() > word.length()) {
                    word = elem;
                }
            }
            while (resultMap.keySet().contains(word)) {
                lexems.remove(word);
                for (String elem : lexems) {
                    if (elem.length() > word.length()) {
                        word = elem;
                    }
                }
            }
            resultMap.put(word, automa.getType());
        }

        for (Map.Entry<String, LexemTypes> obj : resultMap.entrySet()) {
            System.out.println("<"+obj.getKey()+" : "+obj.getValue()+">");
        }
    }
}






















