package account;

public class BankAccount {

	private double balance;
	
	
	public BankAccount(double balance) {
		this.balance = balance;
	}
	
	public void deposit(double amount) throws Exception {
		if(amount <= 0) 
			throw new Exception("Incorrect deposit amount");
		balance +=amount;
		
	}
	
	public void withdraw(double amount) throws Exception {
		if(amount <= 0 || amount > balance)
			throw new Exception("Incorrect withdraw amount");			
		balance -=amount;	
	}
	
	public double getBalance() {
		return balance;
	}
}
