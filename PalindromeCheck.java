package recursion;


public class PalindromeCheck {

	public static void main(String... strings) {
		System.out.println(palindromeCheckPrint("java"));
		System.out.println(palindromeCheckPrint("madamimadam"));
		System.out.println(palindromeCheckPrint("eve"));
		System.out.println(palindromeCheckPrint("1"));
		System.out.println(palindromeCheckPrint(""));
		System.out.println(palindromeCheckPrint("no"));
	}

	private static String palindromeCheckPrint(String string) {
		if(isPalindrome(string)) {
			return string + " is palindrome";
		}
		return string + " is not palindrome";
	}

	private static boolean isPalindrome(String string) {
		if(string.length()< 2)
			return true;
		return string.charAt(0) == string.charAt(string.length()-1) && isPalindrome(string.substring(1, string.length()-1));
	}
}
