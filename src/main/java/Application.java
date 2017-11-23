import task1.UniversalAutoma;
import utils.LexemTypes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Александр on 06.09.2017.
 */
public class Application {
    public static void main(String[] args) {

        String text = readFromFile("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\text.txt");

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

        function(text, map);
        System.out.println(text);
    }

    public static String readFromFile(String path) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                line = line + " ";
                text.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public static void function(String text, Map<Integer, UniversalAutoma> automaMap) {

        for (int i = 0; i < text.length();) {
            List<Integer> lenList = new ArrayList<>();

            for (UniversalAutoma automa: automaMap.values()) {
                lenList.add(automa.findLexemsVar2(text, i));
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
                System.out.println("<"+automaMap.get(index+1).getType()+","+text.substring(i, i+maxLen)+">");
                i += maxLen;
            }
        }
    }
}






















