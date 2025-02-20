package func_style;

import java.util.List;

public final class QueriedBureau {

	//счетчик запросов кредитных бюро на скоринге 
	
	private final List<Integer> queriedBureauArray;
	private final  List<Integer> queriedNumberArray;
	private final int size;

	
	public QueriedBureau(int bureauTotalCount) {
		this.size = bureauTotalCount;
		this.queriedBureauArray = List.of(new Integer[] {});
		this.queriedNumberArray = List.of(new Integer[] {});
	}
	
	public QueriedBureau(int bureauTotalCount, List<Integer> queriedBureau, List<Integer> queriedNumber) {
		this.size = bureauTotalCount;
		this.queriedBureauArray = List.of(queriedBureau.toArray(new Integer[] {}));
		this.queriedNumberArray = List.of(queriedNumber.toArray(new Integer[] {}));
	}

	public int getSize() {
		return size;
	}

	public QueriedBureau setBureauQueried(int bureauNumber) {
		if (bureauNumber > 0 && bureauNumber <= size) {
			Integer[]arr = this.queriedBureauArray.toArray(new Integer[] {});
			arr[bureauNumber - 1] = 1;
			return new QueriedBureau(this.size, List.of(arr), this.queriedNumberArray);
		}
		return this;
	}

	public QueriedBureau setBureauQueriedNumber(int bureauNumber) {

		if (bureauNumber > 0 && bureauNumber <= size) {
			Integer[] arr = this.queriedNumberArray.toArray(new Integer[] {});
			arr[bureauNumber - 1]++;
			return new QueriedBureau(this.size, this.queriedBureauArray, List.of(arr));
		}
		return this;
	}

	public QueriedBureau setBureauQueried(String bureauNumber) {

		int number = Integer.parseInt(bureauNumber);

		return setBureauQueried(number);

	}

	public QueriedBureau setBureauQueriedNumber(String bureauNumber) {
		int number = Integer.parseInt(bureauNumber);
		return setBureauQueriedNumber(number);
	}


	public String getQueriedBureauString() {
		StringBuffer sb = new StringBuffer();
		queriedBureauArray.forEach(i -> sb.append(i));
		return sb.toString();

	}

	public String getQueriedNumberString() {
		StringBuffer sb = new StringBuffer();
		queriedNumberArray.forEach(i -> sb.append(i));
		return sb.toString();
	}

}
