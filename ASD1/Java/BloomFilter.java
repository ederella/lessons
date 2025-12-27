package bloom;

public class BloomFilter {
	public int filter_len;
	public long bites = 0b0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000;

	public BloomFilter(int f_len) {
		filter_len = f_len;
	}

	public int hash1(String str1) {
		long result = 0;
		for (int i = 1; i < str1.length(); i++) {
			result = (result * 17 + (int) str1.charAt(i)) % filter_len;
		}
		return 1 << result;
	}

	public int hash2(String str1) {
		long result = 0;
		for (int i = 1; i < str1.length(); i++) {
			result = (result * 223 + (int) str1.charAt(i)) % filter_len;
		}
		return 1 << result;
	}

	public void add(String str1) {
		bites = bites | hash1(str1) | hash2(str1);
	}

	public boolean isValue(String str1) {
		if ((hash1(str1) & bites) == 0)
			return false;
		if ((hash2(str1) & bites) == 0)
			return false;
		return true;
	}
}
