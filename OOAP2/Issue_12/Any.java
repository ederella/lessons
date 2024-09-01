package main;

import java.lang.reflect.Type;

public non-sealed class Any extends General {
	
	private int id;
	private int status;
	private String author;
	

	public void setId(int id) {
		this.id = id;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public void copyTo(General g) {
		Any a = (Any)g;
		a.setId(id);
		a.setStatus(status);
		a.setAuthor(author);
	}

	@Override
	public Any makeClone() {
		Any a = new Any();
		a.id = this.id;
		a.status = this.status;
		a.author = this.author;
		return a;
	}

	@Override
	public boolean isEqual(General g) {
		if(this == g)
			return true;
		
		if(!(g instanceof Any)) 
			return false;
		
		Any a = (Any)g;
		if(a.id!= this.id)
			return false;
		
		if(a.status!= this.status)
			return false;
			
		if(a.author!= this.author)
			return false;
		
		return true;
	}

	@Override
	public String serialize() {
		
		return "{\n"
				+ "\"id\":\"" + id + "\","
				+ "\"status\":\"" + status + "\","
				+ "\"author\":\"" + author + "\","
				+"\n}";
	}

	@Override
	public void deserialize(String s) {
		s = s.replace("{","");
		s = s.replace("}","");
		s = s.replace("\"","");
		String[] arr = s.split(",");
		
		for(int i = 0; i< arr.length ;i++) {
			String[] subStr = arr[i].split(":");
			if(subStr[0].equals("id"))
				this.id = Integer.parseInt(subStr[1]);
			if(subStr[0].equals("status"))
				this.status = Integer.parseInt(subStr[1]);
			if(subStr[0].equals("author"))
				this.author = subStr[1];
		}

	}

	@Override
	public boolean isTypeOf(Class<?> t) {
		return this.getClass() == t ;
	}

	@Override
	public Class<?> getType() {	
		return this.getClass();
	}

	@Override
	String print() {
		return "id = "+id+"; status = " + status+ "; author = "+ author;
	}

	@Override
	int assignment_attempt(General target, Object source) {
		if(source instanceof General) {
			target = (General)source;
			return 1;
		}
		target = null;
		return 0;
	}
}
