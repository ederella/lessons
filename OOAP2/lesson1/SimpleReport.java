package ooap;

public class SimpleReport {
	private String number;
	private String dateOfReport;
	private String customerName;
	
	public SimpleReport(String number, String dateOfReport, String customerName){
		this.number = number;
		this.dateOfReport = dateOfReport;
		this.customerName = customerName;
	}
	
	
	public String print(){
		return "�����\n �" + number 
				+ "\n ����: " + dateOfReport 
				+ "\n ������: " + customerName;
	}
}
