package flowers;

public class EnglishRose extends WildRose{


	@Override
	public String exudeFragrance() {
		increaseFlowers();
		openFlowers();
		return "Excellent fragrance";
	}
	/*
	 * compilation error  - cannot reduce visibility
	private String attractBees() {
		return "Dear sir bee, would you like to enjoy this delicious nectar?";
	}*/
	
	//ok, linter's warning
	public void increaseFlowers() {
		flowersCounter++;
	}

	//ok, linter's warning
	private void openFlowers() {
		System.out.println("All flowers have been opened!");
	}
}
