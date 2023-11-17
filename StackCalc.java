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

			if (s.matches("\\*|\\+|\\/|\\-")) {
				int operand1 = stack2.pop();
				int operand2 = stack2.pop();
				stack2.push(doOperation(operand1, operand2, s));
			}			
			
			if (s.equals("=")) {
				return stack2.pop();
			}
		}

		return 0;
	}

	private static Integer doOperation(int operand1, int operand2, String operation) {
		if (operation.equals("+"))
			return operand1 + operand2;
		
		if (operation.equals("*"))
			return operand1 * operand2;
		
		if (operation.equals("-"))
			return operand2 - operand1;
		
		if (operation.equals("/"))
			return operand2 / operand1;
		
		throw new UnsupportedOperationException();
	}
}
