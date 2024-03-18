package decisionTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DecisionTreeCalulator {
	ArrayList<Category> categories;
	
	public DecisionTreeCalulator(){
		categories = new ArrayList<Category>();
	}
	
	
	public void fill(String inputCSV) throws Exception{
		Scanner scanner = null;
		try {
			scanner = new Scanner(getClass().getResourceAsStream(inputCSV));
			
			if(!scanner.hasNextLine())
				throw new Exception("File is empty");			

			setCategories(parseLine(scanner.nextLine()));			
			
			while(scanner.hasNextLine()){
				String row = scanner.nextLine();
				if(row.trim().length() == 0)
					continue;
				
				setRowCategoryValues(parseLine(row));
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			scanner.close();
		}
	}

	public HashMap<String, Double> calculateGain(){
		HashMap<String, Double> categoryGains = new HashMap<String, Double>();
		
		Category parentCategory = categories.get(categories.size() - 1);
		
		for (int i = 0; i < categories.size() - 1; i++) {
			Category category = categories.get(i);
			categoryGains.put(category.getName(), category.calculateGain(parentCategory));
		}
		return categoryGains;
	}
	private void setRowCategoryValues(ArrayList<String> values) {
		
		for (int i = 0; i < values.size(); i++) {
			categories.get(i).addKey(values.get(i));
		}
	}

	private void setCategories(ArrayList<String> categoryNames) throws Exception {
		if(categoryNames.isEmpty())
			throw new Exception("Header is empty");	
		
		for (String categoryName : categoryNames) {
			categories.add(new Category(categoryName));
		}
	}
	
	private ArrayList<String> parseLine(String line) {
		ArrayList<String> values = new ArrayList<String>();
		Scanner rowScanner = null;
		try{
			rowScanner = new Scanner(line);
	        rowScanner.useDelimiter(",");
	        while (rowScanner.hasNext()) {
	            values.add(rowScanner.next());
	        }
	    }catch (Exception e) {
	    	rowScanner.close();
		}
	    return values;
	}

}
