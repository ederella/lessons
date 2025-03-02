package abstraction;

import java.lang.reflect.Field;

public class SimplePrinter implements Printer {

	@Override
	public String getString(Object o) {			
		return "{" + getString(o, " ") + "}";
	}

	
	private String getString(Object o, String space) {		
			
			if(isWrapperType(o.getClass()) || o instanceof String) {
				return o.toString();
			}
			
			StringBuffer sb = new StringBuffer();
			sb.append("\n");
			Field[] fields = o.getClass().getDeclaredFields();
	
			for (Field field : fields) {
				field.setAccessible(true);
				Object f = null;
				try {
					f = field.get(o);
				} catch (IllegalArgumentException | IllegalAccessException e) {				
					e.printStackTrace();
				}
				sb.append(space+ field.getName() + ": " + (f == null? "null": getString(f, space+" "))+ "\n");
			}
			return sb.toString();
		}

	
	public void print(Object o) {
		System.out.println(getString(o));
	}
	private boolean isWrapperType(Class<?> clazz) {
	    return clazz.equals(Boolean.class) || 
	        clazz.equals(Integer.class) ||
	        clazz.equals(Character.class) ||
	        clazz.equals(Byte.class) ||
	        clazz.equals(Short.class) ||
	        clazz.equals(Double.class) ||
	        clazz.equals(Long.class) ||
	        clazz.equals(Float.class);
	}
}
