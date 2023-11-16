package alternativeStack;

import java.util.Stack;

public class StackCalc {
	public static int postfixCalc(String string) {

		String arr[] = string.split(" ");
		Stack<String> stack1 = new Stack<String>();
		Stack<Integer> stack2 = new Stack<Integer>();

		for (int i = arr.length - 1; i >= 0; i--) {
			stack1.push(arr[i]);
		}

		while (stack1.size() > 0) {
			String s = stack1.pop();
			if (s.matches("\\d+")) {
				stack2.push(Integer.valueOf(s));
			}

			if (s.equals("*")) {
				stack2.push(stack2.pop() * stack2.pop());
			}

			if (s.equals("+")) {
				stack2.push(stack2.pop() + stack2.pop());
			}

			if (s.equals("/")) {
				int divider = stack2.pop();
				stack2.push(stack2.pop() / divider);
			}

			if (s.equals("-")) {
				int subtrahend = stack2.pop();
				stack2.push(stack2.pop() - subtrahend);
			}
			
			if (s.equals("=")) {
				return stack2.pop();
			}
		}

		return 0;
	}
}
