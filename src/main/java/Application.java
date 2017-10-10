import taskOne.UniversalAutoma;

/**
 * Created by Александр on 06.09.2017.
 */
public class Application {
    public static void main(String[] args) {

//        String deterministicAutomaFile = "C:\\Users\\ErmolaevAM\\Downloads\\FormalLanguages\\src\\main\\resources\\taskOne\\deterministicAutoma.json";
//        String undeterministicAutomaFile = "C:\\Users\\ErmolaevAM\\Downloads\\FormalLanguages\\src\\main\\resources\\taskOne\\undeterministicAutoma.json";
        String taskTwo = "D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\taskTwo\\taskTwoFile.json";

        UniversalAutoma automa = new UniversalAutoma(taskTwo);
//        System.out.println(automa.start("ababbaaa")); //15-0, 15-15, 30-15, 30-30, 30-40, 40-40, a>, A
//        System.out.println(automa.start("ks"));

//        System.out.println(automa.start("+10.12e7"));

            automa.printAllNumbers("+10.12e7abcde7f",1); //поправить
    }
}
