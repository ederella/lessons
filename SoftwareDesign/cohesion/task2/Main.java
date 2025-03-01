package cohesion.task2;

public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        cat.makeSound();
        cat.makeSound(3);//The method makeSound() in the type Animal is not applicable for the arguments (int)
    }
}
