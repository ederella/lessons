package ooap;

import java.math.BigDecimal;

public class Liquid extends Drink{

	public Liquid(float volume) {
		super(volume, new BigDecimal(0));
	}

	public String toString(){
		return "volume: " + volume;
	}
}
