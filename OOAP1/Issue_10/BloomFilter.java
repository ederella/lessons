package ooap1.bloom;

public class BloomFilter extends AbstractBloomFilter{

	public int filterLen;
	public long bites = 0b0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000_0000;

	public BloomFilter(int fLen) {
		filterLen = fLen;
	}

	public int hash1(String str1) {
		long result = 0;
		for (int i = 1; i < str1.length(); i++) {
			result = (result * 17 + (int) str1.charAt(i)) % filterLen;
		}
		return 1 << result;
	}

	public int hash2(String str1) {
		long result = 0;
		for (int i = 1; i < str1.length(); i++) {
			result = (result * 223 + (int) str1.charAt(i)) % filterLen;
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
