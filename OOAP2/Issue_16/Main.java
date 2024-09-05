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
		
		Wolf[] ar = {w, d};
		covariant(ar);
		
		polymorph(d);

	}


	public static <T extends Wolf> void covariant(T[] dogs) {
		for(T val : dogs) {
			System.out.println(val);
		}		
	}
	
	public static void polymorph(Wolf v) {
		System.out.println(v.sayName("Bob"));
	}

}
