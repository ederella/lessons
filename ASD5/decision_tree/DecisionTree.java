package decisionTree;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;


public class DecisionTree {
	class Node{
		String name;
		String parentValue;
		String decision;
		ArrayList<Node> children;		
		
		Node(String name){
			this.name = name;
			children = new ArrayList<Node>();
		}
		
		Node(String name, String value){
			this.name = name;
			this.parentValue = value;
			children = new ArrayList<Node>();
		}
		
		Node(String name, String value, String decision){
			this.name = name;
			this.parentValue = value;
			this.decision = decision;
		}
		
		public String toString() {
			return (parentValue.length()>0?
							"if " +parentValue: "") 
					+ "\n " + name + "? " 
					+ (decision!=null? "\n <" + decision + ">": "") 
					+ (children!=null? "\n  " + Arrays.toString(children.toArray()):"");
			
		}
	}

	Node root;
	ArrayList<Category> categories;
	
	public DecisionTree() {
		categories = new ArrayList<Category>();
	}
	
	public Node getTree() {
		return root;
	}
	
	public DecisionTree create(String inputCSV) throws Exception{	
		
		fill(inputCSV);
		
		buildTree();
		
		return this;
	}
	
	
	private void buildTree() {		
				
		root = buildTree("", categories);
				
	}

	private Node buildTree(String parKey, ArrayList<Category> categories) {	
		
		if(categories.get(categories.size() - 1).listKeysDistinct().size() == 1) {
			return new Node(categories.get(categories.size() - 1).getName(),parKey, categories.get(categories.size() - 1).getKeys().get(0));
		}
		
		HashMap<String, Double> gains = calculateGains(categories);
		
		String newParentCategoryName = getCategoryOfMaxGain(gains);
		
		Node parNode = new Node(newParentCategoryName, parKey);
		
		Category parentCategory = getCategoryByName(newParentCategoryName, categories);
		
		ArrayList<String> listOfKeys = parentCategory.listKeysDistinct();
		
		for (String key : listOfKeys) {
			
			ArrayList<Category> subCategories = filter(key, parentCategory, categories);
	
			parNode.children.add(buildTree(key, subCategories));
		}
		
		return parNode;
		
	}



	private ArrayList<Category> filter(String key, Category parentCategory, ArrayList<Category> categories) {
		ArrayList<Category> filteredCategories = new ArrayList<Category>();

		for (Category category : categories) {
			if(parentCategory.getName().equals(category.getName()))
				continue;
			
			Category newCategory = new Category(category.getName());			
			for(int i = 0; i < category.size(); i++){
				if(parentCategory.getKeys().get(i).equals(key))
					newCategory.addKey(category.getKeys().get(i));				
			}
			filteredCategories.add(newCategory);
		}

		return filteredCategories;
	}


	private Category getCategoryByName(String parentCategoryName, ArrayList<Category> categories) {		
		for (Category category : categories) {
			if(category.getName().equals(parentCategoryName)){
				return category;
			}
		}
		return null;
	}


	private void fill(String inputCSV) throws Exception{
		Scanner scanner = null;
		try {
			scanner = new Scanner(getClass().getResourceAsStream(inputCSV));
			
			if(!scanner.hasNextLine())
				throw new FileNotFoundException("File is empty");			

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
			if(scanner!=null)
				scanner.close();
		}
	}

	public HashMap<String, Double> calculateGains(ArrayList<Category> categories){
		HashMap<String, Double> categoryGains = new HashMap<String, Double>();
		Category resultCategory = categories.get(categories.size() - 1);
		for (int i = 0; i < categories.size() - 1; i++) {
			Category category = categories.get(i);

			categoryGains.put(category.getName(), category.calculateGain(resultCategory));
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
			throw new IllegalArgumentException("Header is empty");	
		
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

	private String getCategoryOfMaxGain(HashMap<String, Double> gains) {
		double maxGain = 0.0d;
		String name = "";
		for (Entry<String, Double> entry : gains.entrySet()) {
			if (entry.getValue() > maxGain) {
				maxGain = entry.getValue();
				name = entry.getKey();
			}
		}
		return name;
	}
}

