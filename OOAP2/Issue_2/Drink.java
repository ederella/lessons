package ooap;

import java.math.BigDecimal;

public class Drink {
	protected float volume;
	protected BigDecimal price;
	
	public Drink(float volume, BigDecimal price){
		this.volume = volume;
		this.price = price;
	}
	
	public String toString(){
		return "volume: " + volume + " price: " + price;
	}
	
}
