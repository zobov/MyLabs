package main;
/**
 * Created by Zobov on 17.04.14.
 */
public class InterpreterRunner {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("You must choose <input file path> <output file path>");
        }
        try {
            new Interpreter(args[0], args[1]).run();
        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}
