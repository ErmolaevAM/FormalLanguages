import taskOne.DeterministicAutomaton;
import taskOne.UniversalDeterministicAutomaton;

/**
 * Created by Александр on 06.09.2017.
 */
public class Application {
    public static void main(String[] args) {
//        DeterministicAutomaton deterministicAutomaton =
//                new DeterministicAutomaton("D:\\ermolaxe\\IdeaProjects\\homedev.testproject\\FormalLanguages\\src\\main\\resources\\taskOne\\data.json");
//        System.out.println(deterministicAutomaton);

        UniversalDeterministicAutomaton automaton =
                new UniversalDeterministicAutomaton("C:\\Users\\ErmolaevAM\\Downloads\\FormalLanguages\\src\\main\\resources\\taskOne\\data.json");
    }
}
