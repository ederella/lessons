package credit;

public class InstantCredit extends Credit{

	@Override
	public void executeIssuance() {
		System.out.println("Instant credit has been issued");
	}

}
