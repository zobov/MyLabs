package main;

import java.io.*;
import java.util.Deque;
import java.util.zip.DataFormatException;

/**
 * Created by Zobov on 17.04.14.
 */
public class Interpreter {
    private BufferedReader input = null;
    private PrintWriter output = null;
    private Deque<String> myDeque;


    public Interpreter()  {         
    	myDeque = new LinkedDeque<>();       
    }

    /**
     * Reads strings from inputFile and runs methods of LinkedDeque object.
     * Results of each operation is written into the outputFile.
     * if methods returns nothing then we just print information about command
     * example - string with command: 'addLast 5' printed value: 'add last 5'
     * @param inputFile  - the file which contains instructions
     * @param outputFileName - the file in which results will be written
     */
    public void run(File inputFile, File outputFile) throws IOException, DataFormatException {
        try {
            input = new BufferedReader(new FileReader(inputFile));
            output = new PrintWriter(new FileWriter(outputFile));
            while (input.ready()) {
                String command = input.readLine();
                runCommand(command);
            }
        } finally {
            closeIOStreams();
        }
    }

    private void runCommand(String command) throws DataFormatException {
        String[] commandLine = command.split(" ");
        switch (commandLine[0]) {
            case "addFirst":
                if (commandLine.length < 2) throw new DataFormatException("Invalid argument for 'addFirst'");
                myDeque.addFirst(commandLine[1]);
                output.println("Add first: " + commandLine[1]);
                break;
            case "addLast":
                if (commandLine.length < 2) throw new DataFormatException("Invalid argument for 'addLast'");
                myDeque.addLast(commandLine[1]);
                output.println("Add last: " + commandLine[1]);
                break;
            case "pollFirst":
                output.println("Poll first: " + myDeque.pollFirst());
                break;
            case "pollLast":
                output.println("Poll last: " + myDeque.pollLast());
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
                throw new DataFormatException("Unknown command " + commandLine[0]);
        }
    }

    private void closeIOStreams() {
        try{
            if (output != null) output.close();
        } catch(Exception e){
            System.err.print("Can't close output file");
        }

        try{
            if (input != null) input.close();
        } catch(Exception e){
            System.err.print("Can't close input file");
        }
    }
}
