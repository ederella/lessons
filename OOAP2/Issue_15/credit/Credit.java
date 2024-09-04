package credit;

import java.math.BigDecimal;

public abstract class Credit {

	Customer customer;
	BigDecimal amount;
	int termMonths;
	BigDecimal interestRate;
	
	public abstract void executeIssuance();
}
