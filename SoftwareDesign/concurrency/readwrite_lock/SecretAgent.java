package readwrite_lock;

import java.util.GregorianCalendar;

public class SecretAgent implements Runnable{

	private SecretData secrets;
	private String name;
	
	public SecretAgent(String name, SecretData secrets){
		this.name = name;
		this.secrets = secrets;
	}
	
	public void run() {
		secrets.write(this.name + " was here at " + new GregorianCalendar().getTime());
		secrets.read();
	}
}