package account;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankAccountTest {

	BankAccount bankAccount;

	@BeforeEach
	public void setUp() {
		bankAccount = new BankAccount(1000.00);
	}

	@Test
    public void testDeposit() {
        assertDoesNotThrow(() -> bankAccount.deposit(1_000_000_000.00));
        System.out.println("after deposit 1_000_000_000.00 : " + bankAccount.getBalance());
        
        assertThrows(Exception.class ,() -> bankAccount.deposit(-10_000_000_000.00));
        System.out.println("after deposit -10_000_000_000.00 : " +bankAccount.getBalance());
     
    }

	@Test
    public void testWithdraw() {
        assertDoesNotThrow(() -> bankAccount.withdraw(1_000));
        System.out.println("after withdraw 1_000.00 : " + bankAccount.getBalance());
        
        assertThrows(Exception.class ,() -> bankAccount.withdraw(1_000_000_000.00));
        System.out.println("after withdraw 1_000_000_000.00.00 : " + bankAccount.getBalance());
        
        assertThrows(Exception.class ,() -> bankAccount.withdraw(-1_000_000_000));
        System.out.println("after withdraw -1_000_000_000.00 : " +bankAccount.getBalance());
    }
	
}
