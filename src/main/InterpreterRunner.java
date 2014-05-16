import java.io.File;
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
        	File inputFile = new File(args[0]);
        	File outputFile = new File(args[1]);
            new Interpreter().run(inputFile, outputFile);
        } catch (IOException | DataFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
