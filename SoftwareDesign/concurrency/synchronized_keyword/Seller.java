package synchronized_keyword;

import java.math.BigDecimal;

public class Seller {
	
	private int productCount;	
	private BigDecimal accountBalance;
	
	public Seller(int productCount, BigDecimal accountBalance){
		this.productCount = productCount;
		this.accountBalance = accountBalance;
	}
	
	public synchronized void sellProduct(BigDecimal price){
		if(productCount > 0 && price.compareTo(BigDecimal.ZERO) > 0){
			productCount = productCount - 1;
			accountBalance = accountBalance.add(price);
		}else{
			System.out.println("Deal is unacceptable!");
		}
		
	}

	public BigDecimal getBalance() {
		return accountBalance;
	}

	public int getCount() {
		return productCount;
	}

}
