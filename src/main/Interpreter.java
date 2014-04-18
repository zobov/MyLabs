package main;

import java.io.*;
import java.util.Deque;
import java.util.zip.DataFormatException;

/**
 * Created by Zobov on 17.04.14.
 */
public class Interpreter {
    BufferedReader input = null;
    PrintWriter output = null;
    Deque<String> myDeque;

    /**
     * @param inputFileName  - path to the file which contains instructions
     * @param outputFileName - path to the file in which results will be written
     */
    public Interpreter(String inputFileName, String outputFileName) throws IOException {
        try{
        File inputFile = new File(inputFileName);
        if (!inputFile.canRead()) {
            throw new IOException("Can't read from '" + inputFileName + "', may be file doesn't exist.");
        }
        input = new BufferedReader(new FileReader(inputFile));
        File outputFile = new File(outputFileName);
        if (!inputFile.canRead()) {
            throw new IOException("Can't write to '" + outputFileName + "'.");
        }
        output = new PrintWriter(new FileWriter(outputFileName));
        myDeque = new LinkedDeque<>();
        } catch (Exception e){
            closeIOStreams();
            throw e;
        }
    }

    /**
     * Reads strings from inputFile and runs methods of LinkedDeque object.
     * Results of each operation is written into the outputFile.
     * if methods returns nothing then we just print information about command
     * example - string with command: 'addLast 5' printed value: 'add last 5'
     */
    public void run() throws IOException {
        try {
            while (input.ready()) {
                String[] commandLine = input.readLine().split(" ");
                switch (commandLine[0]) {
                    case "addFirst":
                        myDeque.addFirst(commandLine[1]);
                        output.println("add first: " + commandLine[1]);
                        break;
                    case "addLast":
                        myDeque.addLast(commandLine[1]);
                        output.println("add last: " + commandLine[1]);
                        break;
                    case "pollFirst":
                        output.println("Poll first: " + myDeque.pollFirst());
                        break;
                    case "pollLast":
                        output.println("Poll last " + myDeque.pollLast());
                        break;
                    case "peekFirst":
                        output.println("Peek first: " + myDeque.peekFirst());
                        break;
                    case "peekLast":
                        output.println("Peek last: " + myDeque.peekLast());
                        break;
                    case "getFirst":
                        output.println("Get first: " + myDeque.getFirst());
                        break;
                    case "getLast":
                        output.println("Get last: " + myDeque.getLast());
                        break;
                    case "isEmpty":
                        output.println("Is empty - " + myDeque.isEmpty());
                        break;
                    case "size":
                        output.println("Size: " + myDeque.size());
                        break;
                    case "toString":
                        output.println("To string: " + myDeque.toString());
                        break;
                    case "clear":
                        output.println("Clear");
                        myDeque.clear();
                        break;
                    default:
                        throw new DataFormatException("unknown command " + commandLine[0]);
                }
            }
        } catch (Exception e) {
            System.err.print(e.getMessage());
        } finally {
            closeIOStreams();
        }
    }
    void closeIOStreams() throws IOException {
        if (output != null) output.close();
        if (input != null) input.close();
    }
}
