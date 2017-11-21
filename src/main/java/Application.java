import task1.UniversalAutoma;
import utils.LexemTypes;

import java.io.*;
import java.util.*;

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

        /*UniversalAutoma asAutoma = new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LOG.json", LexemTypes.LOG);
        System.out.println(asAutoma.findLexems("false", 0))*/;

        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\text.txt"))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                line = line + " ";
                text.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Integer, UniversalAutoma> map = new LinkedHashMap<>();
        map.put(1, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\AS.json", LexemTypes.AS));
        map.put(2, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LS.json", LexemTypes.LS));
        map.put(3, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RS.json", LexemTypes.RS));
        map.put(4, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LB.json", LexemTypes.LB));
        map.put(5, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RB.json", LexemTypes.RB));
        map.put(6, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LC.json", LexemTypes.LC));
        map.put(7, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RC.json", LexemTypes.RC));
        map.put(8, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\COL.json", LexemTypes.COL));
        map.put(9, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\KW.json", LexemTypes.KW));
        map.put(10, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\IN.json", LexemTypes.IN));
        map.put(11, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RN.json", LexemTypes.RN));
        map.put(12, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LOG.json", LexemTypes.LOG));
        map.put(13, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\NIL.json", LexemTypes.NIL));
        map.put(14, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\OP.json", LexemTypes.OP));
        map.put(15, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\WS.json", LexemTypes.WS));
        map.put(16, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\COM.json", LexemTypes.COM));
        map.put(17, new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\ID.json", LexemTypes.ID));

        List<UniversalAutoma> automaList = new ArrayList<>();
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\AS.json", LexemTypes.AS));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LS.json", LexemTypes.LS));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RS.json", LexemTypes.RS));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LB.json", LexemTypes.LB));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RB.json", LexemTypes.RB));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LC.json", LexemTypes.LC));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RC.json", LexemTypes.RC));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\COL.json", LexemTypes.COL));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\KW.json", LexemTypes.KW));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\IN.json", LexemTypes.IN));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\RN.json", LexemTypes.RN));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\LOG.json", LexemTypes.LOG));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\NIL.json", LexemTypes.NIL));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\OP.json", LexemTypes.OP));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\WS.json", LexemTypes.WS));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\COM.json", LexemTypes.COM));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\ID.json", LexemTypes.ID));

//        function(text.toString(), map);
        function(text.toString(), automaList);
        System.out.println(text.toString());

    }

    public static void function(String text, Map<Integer, UniversalAutoma> automaMap) {
        for (int i = 0; i < text.length();) {
            List<Integer> lenList = new ArrayList<>();
            for (UniversalAutoma automa: automaMap.values()) {
                lenList.add(automa.findLexems(text, i));
            }
            int maxLen = 0;
            int index = -1;
            for (int j = 0; j < lenList.size(); j++) {
                if (lenList.get(j) > maxLen) {
                    maxLen = lenList.get(j);
                    index = j;
                }
            }
            if (maxLen == 0) {
                i++;
            } else {
                System.out.println("<"+automaMap.get(index+1)+","+text.substring(i, i+maxLen)+">");
                i += maxLen;
            }
        }
    }

    public static void function(String text, List<UniversalAutoma> automaList) {
        for (int i = 0; i < text.length();) {
            List<Integer> lenList = new ArrayList<>();
            for (UniversalAutoma automa: automaList) {
                lenList.add(automa.findLexems(text, i));
            }
            int maxLen = 0;
            int index = -1;
            for (int j = 0; j < lenList.size(); j++) {
                if (lenList.get(j) > maxLen) {
                    maxLen = lenList.get(j);
                    index = j;
                }
            }
            if (maxLen == 0) {
                i++;
            } else {
                System.out.println("<"+automaList.get(index)+","+text.substring(i, i+maxLen)+">");
                i += maxLen;
            }
        }
    }
}






















