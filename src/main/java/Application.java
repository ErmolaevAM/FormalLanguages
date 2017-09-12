import taskOne.UniversalDeterministicAutomaton;

/**
 * Created by Александр on 06.09.2017.
 */
public class Application {
    public static void main(String[] args) {
        UniversalDeterministicAutomaton automaton =
                new UniversalDeterministicAutomaton("D:\\ermolaxe\\GitProject\\FormalLanguages\\src\\main\\resources\\taskOne\\data.json");

        automaton.start("ababab");
    }
}
