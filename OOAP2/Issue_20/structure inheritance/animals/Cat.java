package animals;

public class Cat extends Animal implements DisasterMaker{

	@Override
	public String sayName() {
		return "Cat";
	}

	public String makeDisaster() {
		return "Destroy the house!";
	}

}
