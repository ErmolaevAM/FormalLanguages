import task1.UniversalAutoma;
import utils.LexemTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Александр on 06.09.2017.
 */
public class Application {
    public static void main(String[] args) {

        /*TASK 3*/
        String text = readFromFile("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\text.txt");

        Map<Integer, UniversalAutoma> map = new LinkedHashMap<>();
        map.put(1, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\AS.json", LexemTypes.AS));
        map.put(2, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\LS.json", LexemTypes.LS));
        map.put(3, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\RS.json", LexemTypes.RS));
        map.put(4, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\LB.json", LexemTypes.LB));
        map.put(5, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\RB.json", LexemTypes.RB));
        map.put(6, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\LC.json", LexemTypes.LC));
        map.put(7, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\RC.json", LexemTypes.RC));
        map.put(8, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\COL.json", LexemTypes.COL));
        map.put(9, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\KW.json", LexemTypes.KW));
        map.put(10, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\IN.json", LexemTypes.IN));
        map.put(11, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\RN.json", LexemTypes.RN));
        map.put(12, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\LOG.json", LexemTypes.LOG));
        map.put(13, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\NIL.json", LexemTypes.NIL));
        map.put(14, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\OP.json", LexemTypes.OP));
        map.put(15, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\WS.json", LexemTypes.WS));
        map.put(16, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\COM.json", LexemTypes.COM));
        map.put(17, new UniversalAutoma("C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\ID.json", LexemTypes.ID));

        function(text, map, "C:\\Users\\contest\\Downloads\\FormalLanguages-master\\src\\main\\resources\\task3\\output.txt");
        System.out.println(text);


        /*TASK 4*/
//        TestParser.parse("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task4\\com.txt");
    }

    public static String readFromFile(String path) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                line = line + "\n";
                text.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    public static void function(String text, Map<Integer, UniversalAutoma> automaMap, String outputFile) {
        try (FileWriter writer = new FileWriter(outputFile, false)) {
            for (int i = 0; i < text.length(); ) {
                List<Integer> lenList = new ArrayList<>();

                for (UniversalAutoma automa : automaMap.values()) {
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
                    String word = text.substring(i, i + maxLen);
                    if (word.contains("\n")) {
                        word = word.replace("\n", "\\n");
                    }
                    if (word.contains("\t")) {
                        word = word.replace("\t", "\\t");
                    }
                    writer.write("<" + automaMap.get(index + 1).getType() + "," + word + ">" + "\n");
                    i += maxLen;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






















