package synchronized_keyword;

import java.math.BigDecimal;

public class Customer implements Runnable{
	
	private Seller seller;
	
	public Customer(Seller seller){
		this.seller = seller;
	}

	private void buy(BigDecimal price){
		seller.sellProduct(price);
	}

	public void run() {
		buy(new BigDecimal(10.0));
	}
}
