import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Manager {

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
			List<Integer> maxValues = Collections.synchronizedList(new ArrayList<Integer>());
			ExecutorService es = Executors.newFixedThreadPool(3);
			while (br.ready()) {
				String inputData = br.readLine();
				if (inputData.isEmpty()) {
					continue;
				}
				es.execute(new Maximum(inputData, maxValues));
			}
			es.shutdown();
			boolean isTerminated = false;
			while (!isTerminated) {
				isTerminated = es.awaitTermination(1, TimeUnit.HOURS);
			}
			if (maxValues.isEmpty()) {
				System.out.println("Inputfile was empty");
				return;
			}
			int result = maxValues.get(0);
			for (int temp : maxValues) {
				result = Math.max(temp, result);
			}
			System.out.println("Max value = " + result);

		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
}
