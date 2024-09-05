package main;

public class Dog extends Wolf{

	@Override
	String sayName(Object name) {
		String result = ((String)name).toUpperCase() + " is a good boy!";
        return result;

	}
}
