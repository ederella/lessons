package credit;

public class Main {

	public static void main(String[] args) {
		Credit instant = new InstantCredit();
		Credit auto = new AutoCredit();
		
		instant.executeIssuance();
		auto.executeIssuance();

	}

}
