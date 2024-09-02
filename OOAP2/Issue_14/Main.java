package ooap2_14;

public class Main {
	

	public static void main(String... str) {
		Any a = new Any();
		a.setId(1);
		a.setAuthor("Name");
		a.setStatus(1);
		
		Any b = new Any();
		b.setId(2);
		b.setAuthor("Other");
		b.setStatus(2);
		
		VectorObj<Any> v1 = new VectorObj<Any>(2);
		v1.add(a);
		v1.add(b);
		
		VectorObj<VectorObj<Any>> v2 = new VectorObj<VectorObj<Any>>(2);
		v2.add(v1);
		v2.add(v1);
		
		VectorObj<VectorObj<VectorObj<Any>>> v3 = new VectorObj<VectorObj<VectorObj<Any>>>(2);
		v3.add(v2);
		v3.add(v2);
		
		VectorObj<VectorObj<VectorObj<Any>>> v3double = v3.sum(v3);

		System.out.println(v3double);
		
	}
}
