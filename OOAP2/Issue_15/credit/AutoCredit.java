package credit;

public class AutoCredit extends Credit{
	
	@Override
	public void executeIssuance() {
		System.out.println("Auto credit has been issued");
	}

}
