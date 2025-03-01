package cohesion.task2;

public class Cat extends Animal{
    @Override
    public void makeSound(int numberOfSounds) {//compilation error The method makeSound(int) of type Cat must override or implement a supertype method
        for (int i = 0; i < numberOfSounds; i++) {
            System.out.println("Meow");
        }
    }
    
    @Override
    public void makeSound() {
        System.out.println("Meow");
    }
}
