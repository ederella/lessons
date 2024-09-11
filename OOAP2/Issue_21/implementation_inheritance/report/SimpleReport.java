package report;

public class SimpleReport implements Report{

	@Override
	public String generate() {
		return "Simple report generated";
	}

	@Override
	public String print() {
		return "Simple report printed";
	}

}
