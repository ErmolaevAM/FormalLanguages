import task1.UniversalAutoma;
import utils.LexemTypes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

        String text = readFromFile("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\text.txt");

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
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\OP.json", LexemTypes.OP));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\WS.json", LexemTypes.WS));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\NIL.json", LexemTypes.NIL));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\COM.json", LexemTypes.COM));
        automaList.add(new UniversalAutoma("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\task3\\ID.json", LexemTypes.ID));

//        for (UniversalAutoma automa : automaList) {
//            System.out.println(automa.getType());
//        }

        System.out.println(text);
        function(text, automaList);

    }

    public static void function(String text, List<UniversalAutoma> automaList) {
        for (int i = 0; i < text.length();) {
            List<Integer> lenList = new ArrayList<>();
            for (UniversalAutoma automa: automaList) {
//                lenList.add(automa.findLexems(text, i));
                lenList.add(automa.findLexemsVar2(text, i));
            }
//            System.out.println(lenList);
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
                System.out.println("<"+automaList.get(index).getType()+","+text.substring(i, i+maxLen)+">");
                i += maxLen;
            }
        }
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
        return String.valueOf(text);
    }
}






















