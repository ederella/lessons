package recursion;

public class PalindromeCheck {

	public static void main(String... strings) {
		System.out.println(palindromeCheckPrint("java"));
		System.out.println(palindromeCheckPrint("madamimadam"));
		System.out.println(palindromeCheckPrint("aa"));
		System.out.println(palindromeCheckPrint("1"));
		System.out.println(palindromeCheckPrint(""));
		System.out.println(palindromeCheckPrint("no"));
	}

	private static String palindromeCheckPrint(String string) {
		if (isPalindrome(string)) {
			return string + " is palindrome";
		}
		return string + " is not palindrome";
	}

	private static boolean isPalindrome(String string) {
		return isPalindrome(string, 0);
	}

	private static boolean isPalindrome(String string, int startPosition) {
		if (string.length() / 2 <= startPosition)
			return true;
		if(string.charAt(startPosition) != string.charAt(string.length() - 1 - startPosition))
			return false;
		return isPalindrome(string, startPosition + 1);
	}
}
