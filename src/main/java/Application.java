import task1.UniversalAutoma;

/**
 * Created by Александр on 06.09.2017.
 */
public class Application {
    public static void main(String[] args) {

        /*String deterministicAutomaFile =
                "C:\\Users\\ErmolaevAM\\Downloads\\FormalLanguages\\src\\main\\resources\\task1\\deterministicAutoma.json";*/
        String undeterministicAutomaFile =
                "C:\\Users\\contest\\Downloads\\FormalLanguages\\src\\main\\resources\\task1\\undeterministicAutoma.json";
        String taskTwo = "C:\\Users\\contest\\Downloads\\FormalLanguages\\src\\main\\resources\\task2\\taskTwoFile.json";

        UniversalAutoma automa = new UniversalAutoma(taskTwo);

        //детерминированный
//        System.out.println(automa.start("ababbaaa")); //15-0, 15-15, 30-15, 30-30, 30-40, 40-40, a>, A

        //недетерминированный
        /*System.out.println(automa.start("ks"));
        System.out.println(automa.start("kk"));
        System.out.println(automa.start("kkkkkk"));
*/
        //вещественные
        String word1 = "sda17qeq-123qw[e+0.42e+555+12em34e1lk+0.1+1e+3+e+4.4.45ek"; //работает
        automa.printAllNumbers(word1, 1);
        System.out.println("------------------");
        automa.returnMaxNumberLength(word1, 1);
    }
}
