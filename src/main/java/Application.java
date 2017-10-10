import taskOne.UniversalAutoma;

/**
 * Created by Александр on 06.09.2017.
 */
public class Application {
    public static void main(String[] args) {

//        String deterministicAutomaFile = "C:\\Users\\ErmolaevAM\\Downloads\\FormalLanguages\\src\\main\\resources\\taskOne\\deterministicAutoma.json";
//        String undeterministicAutomaFile = "C:\\Users\\ErmolaevAM\\Downloads\\FormalLanguages\\src\\main\\resources\\taskOne\\undeterministicAutoma.json";
        String taskTwo = "C:\\Users\\ErmolaevAM\\Downloads\\FormalLanguages\\src\\main\\resources\\taskTwo\\taskTwoFile.json";

        UniversalAutoma automa = new UniversalAutoma(taskTwo);
//        System.out.println(automa.start("ababbaaa")); //15-0, 15-15, 30-15, 30-30, 30-40, 40-40, a>, A
//        System.out.println(automa.start("ks"));

//        System.out.println(automa.start("+10.12e7"));

        String word1 = "+.fjdh-55.332+45.3e+3.22sfdg34-45jdks34.4E4+.345.453.345+..e.ehjgt654+768+57j.5e+0";
        String word2 = "+10.12e7abcde10f";
        automa.printAllNumbers(word2, 1); //поправить
    }
}
