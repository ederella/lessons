package ooap2_14;

public abstract class General implements Summable<General>{

	abstract void copyTo(General g);

	abstract General makeClone();

	abstract boolean isEqual(General g);

	abstract String serialize();

	abstract void deserialize(String s);

	abstract String print();

	abstract boolean isTypeOf(Class<?>  t);
	
	abstract Class<?> getType();
	
	abstract <A extends General, B extends General> A assignment_attempt(A target, B source);

}