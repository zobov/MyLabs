import java.util.List;
import java.util.StringTokenizer;

public class Maximum implements Runnable{
	private List<Integer> finalList;
	StringTokenizer st;

	public Maximum(String data, List<Integer> finalList) {
		st = new StringTokenizer(data);
		this.finalList = finalList;
	}

	@Override
	public void run() {
		int result = Integer.parseInt(st.nextToken());
		while (st.hasMoreTokens()) {
			result = Math.max(result, Integer.parseInt(st.nextToken()));
		}
		finalList.add(result);
	}
}
