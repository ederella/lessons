package storage;

public class Main {

	public static void main(String[] args) {
		Storage storage = new DataBaseStorage();
		
		storage.save("Moscow");
		storage.save("St.Petersburg");
		storage.save("Novosibirsk");
		
		System.out.println(storage.retrieve(1));
		System.out.println(storage.retrieve(2));
		System.out.println(storage.retrieve(3));
		
		
	}

}
