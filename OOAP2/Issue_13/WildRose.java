package flowers;

public class WildRose {

	public int flowersCounter = 0;
	
	public String exudeFragrance() {
		increaseFlowers();
		return "Simple fragrance";
	}
	
	public String attractBees() {
		openFlowers();
		return "Pss, dude, don't you want to take some nectar?";
	}
	
	private void openFlowers() {
		System.out.println("All flowers have been opened!");
	}
	
	private void increaseFlowers() {
		flowersCounter++;
	}

}
