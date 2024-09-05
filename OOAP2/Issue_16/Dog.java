package main;

public class Dog extends Wolf{

	@Override
	String sayName(String name) {
		String result = name.toUpperCase() + " is a good boy!";
        return result;

	}
}
