package readwrite_lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SecretData {
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
	private final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
	
	private List<String> secrets;
	
	public SecretData(){
		secrets = new ArrayList<String>();
	}
	
	public void read() {
		readLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " started reading secrets");
			for (String secret : secrets) {				
				System.out.println(Thread.currentThread().getName() + " secret: " + secret);
			}
			System.out.println(Thread.currentThread().getName() + " finished reading secret");
		} finally {
			readLock.unlock();
		}
	}
	
	public void write(String secret){
		writeLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " start writing secret " + secret);
			secrets.add(secret);
			System.out.println(Thread.currentThread().getName() + " finished writing secret");
			
		} finally {
			writeLock.unlock();
		}
	}
	
}
