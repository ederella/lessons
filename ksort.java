package sort;

public class ksort {

	public String[] items;

	public ksort() {
		items = new String[800];
	}

	public int index(String s) {
		if (!s.matches("[a-h]\\d{2}"))
			return -1;

		return (int) s.charAt(0) - 97 + Integer.valueOf(s.substring(1));
	}

	public boolean add(String s) {
		int ind = index(s);
		if (ind == -1) {
			return false;
		}
		items[ind] = s;
		return true;
	}
}
