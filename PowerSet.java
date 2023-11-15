package set;

import java.util.ArrayList;

public class PowerSet {

	private ArrayList<String> set;

	public PowerSet() {
		set = new ArrayList<>();
		set.ensureCapacity(20000);
	}

	public int size() {
		return set.size();
	}

	public void put(String value) {
		if (!get(value))
			set.add(value);
	}

	public boolean get(String value) {
		return set.contains(value);
	}

	public boolean remove(String value) {
		if (get(value)) {
			set.remove(value);
			return true;
		}
		return false;
	}

	public PowerSet intersection(PowerSet set2) {
		PowerSet resultSet = new PowerSet();
		for(String s: set) {
			if(set2.get(s))
				resultSet.put(s);
		}		
		return resultSet;
	}

	public PowerSet union(PowerSet set2) {
		PowerSet resultSet = new PowerSet();
		
		for(String s: set) {
			resultSet.put(s);
		}
		for(String s: set2.toArrayList()) {
			resultSet.put(s);
		}
		return resultSet;
	}

	public PowerSet difference(PowerSet set2) {
		PowerSet resultSet = new PowerSet();
		for(String s: set) {
			if(!set2.get(s))
				resultSet.put(s);
		}
		
		return resultSet;
	}

	public boolean isSubset(PowerSet set2) {
		for(String s: set2.toArrayList()) {
			if(!get(s))
				return false;
		}
		return true;
	}
	public ArrayList<String> toArrayList(){
		return new ArrayList<String>(set);
	}
}