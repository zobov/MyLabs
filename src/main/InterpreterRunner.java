import java.io.IOException;
import java.util.zip.DataFormatException;

/**
 * Created by Zobov on 17.04.14.
 */
public class InterpreterRunner {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("You must choose <input file path> <output file path>");
            return;
        }
        try {
            new Interpreter(args[0], args[1]).run();
        } catch (IOException | DataFormatException e) {
            System.err.print(e.getMessage());
        } catch (Exception e) {
            System.err.print(e);
        }
    }
}
