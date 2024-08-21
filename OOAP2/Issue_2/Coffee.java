package ooap;

import java.math.BigDecimal;

public class Coffee extends Drink{
	protected String typeOfBeans;
	protected boolean hot;
	protected boolean withMilk;
	
	public Coffee(float volume, BigDecimal price, String typeOfBeans, boolean hot, boolean withMilk) {
		super(volume, price);
		this.typeOfBeans = typeOfBeans;
		this.hot = hot;
		this.withMilk = withMilk;
	}
	
	public String toString(){
		return super.toString() + " typeOfBeans: "+typeOfBeans + " hot: " + hot + " withMilk: " + withMilk;
	}
}
