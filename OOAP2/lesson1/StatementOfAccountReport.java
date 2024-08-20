package ooap;

import java.math.BigDecimal;

public class StatementOfAccountReport extends SimpleReport{
	private String accountNumber;
	private BigDecimal accountBalance;
	
	public StatementOfAccountReport(String number, String dateOfReport, String customerName, String accountNumber, BigDecimal accountBalance) {
		super(number, dateOfReport, customerName);
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
	}

	@Override
	public String print(){
		return super.print()
				+ "\n выписка по счету: "+ accountNumber 
				+ "\n баланс: " + accountBalance;
	}

}
