package main;

import java.lang.reflect.Type;

public sealed abstract class General permits Any {

	abstract void copyTo(General g);

	abstract General makeClone();

	abstract boolean isEqual(General g);

	abstract String serialize();

	abstract void deserialize(String s);

	abstract String print();

	abstract boolean isTypeOf(Class<?>  t);
	
	abstract Class<?> getType();
	
	abstract <A extends Any, B extends Any> A assignment_attempt(A target, B source);


}
