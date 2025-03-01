package cohesion.task1;

public class Main {

	public static void main(String[] args) {
        Animal myCat = new Cat();
        myCat.makeSound();  // Compilation error: The method makeSound() is undefined for the type Animal
    }
}
