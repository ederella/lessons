package alternativeStack;

import java.util.Stack;

public class StackCheckParenthesis {
	public static boolean isBalanced(String string) {

		char[] chars = string.toCharArray();
		Character ch = Character.valueOf('(');
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] == ')' && stack.peek() == null)
				return false;
			if (chars[i] == ')')
				stack.pop();			
			if (chars[i] == '(')
				stack.push(ch);
		}

		if (stack.size() == 0)
			return true;

		return false;

	}
}
