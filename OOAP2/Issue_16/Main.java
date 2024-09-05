package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;



public class Main {

	public static void main(String[] args) throws IOException {
	
		Wolf w = new Wolf();
		Dog d = new Dog();
		System.out.println(w.sayName("Wolf"));
		System.out.println(d.sayName("Dog"));
		w = d;
		System.out.println(d.sayName("Dog"));

	}



}
