import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class Manager {

	public static void main(String[] Args) {
		List<Integer> maxValues = Collections.synchronizedList(new ArrayList<Integer>());
		ExecutorService es = Executors.newFixedThreadPool(3);
		List<String> data;
		try {
			data = getData(Args[0]);	
			for (String s : data) {
				es.execute(new Maximum(parseData(s), maxValues));
			}			
			es.shutdown();		
			while(!es.isTerminated());	
			if (maxValues.isEmpty()) {
				System.out.println("Inputfile was empty");
				return;
			} 
			int result = maxValues.get(0);
			for (int temp : maxValues) {
				result = Math.max(temp, result);
			}
			System.out.println("Max value = " + result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static List<String> getData(String fileName) throws IOException {
		BufferedReader br = null;
		List<String> list = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(fileName));
			while(br.ready()) {
				list.add(br.readLine());
			}

		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	private static int[] parseData(String data) {
		String[] args = data.split(" ");
		int[] mas = new int[args.length];
		int i = 0;
		for (String s : args) {
			mas[i++] = Integer.parseInt(s);
		}			
		return mas;
	}
}
