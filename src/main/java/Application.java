import taskOne.UniversalDeterministicAutomaton;
import taskOne.UniversalUndeterministicAutomation;

/**
 * Created by Александр on 06.09.2017.
 */
public class Application {
    public static void main(String[] args) {
        UniversalDeterministicAutomaton automaton =
                new UniversalDeterministicAutomaton("C:\\Users\\ErmolaevAM\\Downloads\\FormalLanguages\\src\\main\\resources\\taskOne\\data.json");

        UniversalUndeterministicAutomation undeterministicAutomation =
                new UniversalUndeterministicAutomation("C:\\Users\\ErmolaevAM\\Downloads\\FormalLanguages\\src\\main\\resources\\taskOne\\task2automa.json");

//        automaton.start("abababaa");

        undeterministicAutomation.start("u");
    }
}
