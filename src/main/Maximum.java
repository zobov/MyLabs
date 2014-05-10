import java.util.List;

public class Maximum implements Runnable{
	private List<Integer> finalList;
	private int[] array;

	public Maximum(int[] array, List<Integer> finalList) {
		this.array = array;
		this.finalList = finalList;
	}

	@Override
	public void run() {
		int max = array[0];
		for (int i = 0; i < array.length; i++) {
			max = Math.max(array[i], max);
		}

		finalList.add(max);

	}
}
