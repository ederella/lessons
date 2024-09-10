package birds;

public class Penguin extends Bird{

	@Override
	public String sing() {
		return "Squeak";
	}

	@Override
	public String fly() {
		return "Can't fly";
	}

}
