package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.DataFormatException;

import main.Interpreter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class TestInterpreter {


	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Test
	public void testPrintedValues() throws IOException, DataFormatException {;
		File inputFile = folder.newFile("input.txt");
		File outputFile = folder.newFile("output.txt");

		PrintWriter pw = new PrintWriter(inputFile);
		try {
			pw.println("addFirst 5");
			pw.println("addFirst 3");
			pw.println("addLast 4");
			pw.println("pollFirst");
			pw.println("pollLast");
			pw.println("peekFirst");
			pw.println("peekLast");		
		} finally {
			pw.close();
		}
		String[] results = {"Add first: 5", "Add first: 3", "Add last: 4", 
				"Poll first: 3", "Poll last: 4", "Peek first: 5", "Peek last: 5"};
		
		new Interpreter().run(inputFile, outputFile);
		
		BufferedReader br = new BufferedReader(new FileReader(outputFile)); 
		try {
			for (String result : results) {
				assertEquals(br.readLine(), result);
			}
			if (br.ready()) fail();
		} finally {
			br.close();
		}	
	}
	
	@Test(expected=RuntimeException.class)
	public void testRuntimeException() throws IOException, DataFormatException {;
		File inputFile = folder.newFile("input.txt");
		File outputFile = folder.newFile("output.txt");

		PrintWriter pw = new PrintWriter(inputFile);
		try {
			pw.println("pollFirst");			
		} finally {
			pw.close();
		}
		new Interpreter().run(inputFile, outputFile);
	}
	
	@Test(expected=DataFormatException.class)
	public void testDataFormatExceptionWithAddFirst() throws IOException, DataFormatException {;
		File inputFile = folder.newFile("input.txt");
		File outputFile = folder.newFile("output.txt");

		PrintWriter pw = new PrintWriter(inputFile);
		try {
			pw.println("addFirst");			
		} finally {
			pw.close();
		}
		new Interpreter().run(inputFile, outputFile);
	}
	
	@Test(expected=DataFormatException.class)
	public void testDataFormatExceptionWithAddLast() throws IOException, DataFormatException {;
		File inputFile = folder.newFile("input.txt");
		File outputFile = folder.newFile("output.txt");

		PrintWriter pw = new PrintWriter(inputFile);
		try {
			pw.println("addLast");			
		} finally {
			pw.close();
		}
		new Interpreter().run(inputFile, outputFile);
	}
	
	@Test(expected=DataFormatException.class)
	public void testDataFormatExceptionWithInvalidCommand() throws IOException, DataFormatException {;
		File inputFile = folder.newFile("input.txt");
		File outputFile = folder.newFile("output.txt");

		PrintWriter pw = new PrintWriter(inputFile);
		try {
			pw.println("invalidCommand");			
		} finally {
			pw.close();
		}
		new Interpreter().run(inputFile, outputFile);
	}

	
}
