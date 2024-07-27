package interval_tree;

public class Interval implements Comparable<Interval> {
	int from;
	int to;

	public Interval(int from, int to) {
		this.from = from;
		this.to = to;
	}

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public int getMedian() {
		return (to - from) / 2;
	}

	@Override
	public int compareTo(Interval o) {
		int dif = from - o.from;
		return (dif) != 0 ? dif : to - o.to;
	}

	public String toString() {
		return "[" + from + ", " + to + "]";
	}
}
